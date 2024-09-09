package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureData
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class RestAdapter(private val networkHandler: NetworkHandler) {

    fun <T, R> make(call: Call<T>, transform: (T) -> R): Either<FailureType, R> {
        return when (networkHandler.isConnected) {
            true -> execute(call, transform)
            false, null -> Either.Left(FailureType.NetworkConnectionError)
        }
    }

    private fun <T, R> execute(call: Call<T>, transform: (T) -> R): Either<FailureType, R> {
        return try {
            val request = call.request().toString();
            val response = call.execute()

            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body()!!)))
                false -> Either.Left(getFailureType(response))
            }
        } catch (exception: IOException) {
            Either.Left(FailureType.NetworkConnectionError)
        } catch (exception: Throwable) {
            Either.Left(FailureType.ServerError)
        }
    }

    fun <T> getFailureType(response: Response<T>): FailureType {
        val failureData = FailureData()
        response.errorBody()?.let {
            val jsonError = JsonParser.parseString(it.string()).getAsJsonObject()
            for ((key, jsonElement) in jsonError.entrySet()) {
                if(jsonElement.isJsonPrimitive) {
                    failureData.params.put(key, jsonElement.asString)
                }
                if(jsonElement.isJsonObject) {
                    val mapPrimitives = (jsonElement as? JsonObject)?.entrySet()?.filter { it.value.isJsonPrimitive }?.map {Pair(it.key, it.value.toString())}?.toMap()
                    mapPrimitives?.let { failureData.complexParams.put(key, it) }
                }
            }
        }
        if(response.code() == 422 && !failureData.params.isEmpty()) {
            return FailureType.UnprocessibleEntityError.apply { initMapError(failureData.params) }
        } else if(response.code() == 401) {
            return FailureType.UnauthorizedError
        }
        return FailureType.ServerError
    }
}