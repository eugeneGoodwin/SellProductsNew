package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter


import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureData
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.Locked
import io.ktor.http.HttpStatusCode.Companion.TooManyRequests
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class RestCallAdapter(val httpClient: HttpClient) {

    suspend inline fun <reified T, R> make(block: HttpRequestBuilder.() -> Unit, transform: (T) -> R): Either<FailureType, R> {
        return execute(block, transform)
    }

    suspend inline fun <reified T, R> makeWithCookie(block: HttpRequestBuilder.() -> Unit, transform: (T, String) -> R): Either<FailureType, R> {
        return executeWithCookie(block, transform)
    }

    suspend inline fun <reified T, R> execute(block: HttpRequestBuilder.() -> Unit, transform: (T) -> R): Either<FailureType, R> {
        return try {
            val response = httpClient.request(block)
            if (response.status == HttpStatusCode.OK)
                Either.Right(transform(response.body()))
            else
                Either.Left(getFailureType(response))
        } catch (e: Throwable) {
            val error = e.message
            Either.Left(FailureType.ServerError)
        }
    }

    suspend inline fun <reified T, R> executeWithCookie(block: HttpRequestBuilder.() -> Unit, transform: (T, String) -> R): Either<FailureType, R> {
        return try {
            val response = httpClient.request(block)
            var cookie = ""
            val listHeaders = response.headers.getAll("set-cookie")
            listHeaders?.filter { it.contains("PHPSESSID") }?.firstOrNull()?.let { cookie = it }
            if (response.status == HttpStatusCode.OK)
                Either.Right(transform(response.body(), cookie))
            else
                Either.Left(getFailureType(response))
        } catch (exception: Throwable) {
            Either.Left(FailureType.ServerError)
        }
    }

    suspend fun getFailureType(response: HttpResponse): FailureType {
        val failureData = FailureData()
        response.bodyAsText()?.let {
            try {
                val jsonError = Json.parseToJsonElement(it).jsonObject
                for (element in jsonError.entries) {
                    if (element.value.isJsonPrimitive()) {
                        failureData.params.put(element.key, element.value.jsonPrimitive.content)
                    }
                    if (element.value.isJsonObject()) {
                        val mapPrimitives = (element.value as? JsonObject)?.entries
                            ?.filter { it.value.isJsonPrimitive() }
                            ?.map { Pair(it.key, it.value.toString()) }?.toMap()
                        mapPrimitives?.let { failureData.complexParams.put(element.key, it) }
                    }
                }
            } catch (ex: Throwable) {
                FailureType.ServerError
            }
        }
        if(response.status == HttpStatusCode.UnprocessableEntity && !failureData.params.isEmpty()) {
            return FailureType.UnprocessibleEntityError.apply { initMapError(failureData.params, failureData.complexParams) }
        } else if(response.status == Unauthorized) {
            return FailureType.UnauthorizedError
        } else if(response.status == TooManyRequests && !failureData.params.isEmpty()) {
            return FailureType.TooManyRequestError.apply { init(failureData) }
        } else if(response.status == Locked && !failureData.params.isEmpty()) {
            return FailureType.ResponseDetailsError.apply { init(failureData) }
        }
        return FailureType.ServerError
    }
}

fun JsonElement.isJsonObject(): Boolean = this is JsonObject
fun JsonElement.isJsonPrimitive(): Boolean = this is JsonPrimitive
fun JsonElement.isJsonNull(): Boolean = this is JsonNull
