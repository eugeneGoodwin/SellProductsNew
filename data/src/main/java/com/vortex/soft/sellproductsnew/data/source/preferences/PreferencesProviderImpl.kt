package com.vortex.soft.sellproductsnew.data.source.preferences

import android.content.SharedPreferences
import com.vortex.soft.sellproductsnew.data.repository.common.PreferencesProvider
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.common.type.None

class PreferencesProviderImpl(val prefs: SharedPreferences) : PreferencesProvider {

    override fun getToken(): Either<FailureType, String> {
        return Either.Right(getTokenValue())
    }

    fun getTokenValue(): String {
        return prefs.getString(JWT_TOKEN, "") ?: ""
    }

    override fun saveToken(token: String): Either<FailureType, None> {
        prefs.edit().apply {
            putString(JWT_TOKEN, token)
        }.apply()

        return Either.Right(None())
    }

    override fun removeToken(): Either<FailureType, None> {
        prefs.edit().apply {
            remove(JWT_TOKEN)
        }.apply()

        return Either.Right(None())
    }

    override fun getOrderId(): String {
        return prefs.getString(ORDER_ID, "") ?: ""
    }

    override fun saveOrderId(orderId: String) {
        prefs.edit().apply {
            putString(ORDER_ID, orderId)
        }.apply()
    }

    override fun removeOrderId() {
        prefs.edit().apply {
            remove(ORDER_ID)
        }.apply()
    }

    override fun getCurrentUserId(): String {
        return prefs.getString(USER_ID, "") ?: ""
    }

    override fun saveCurrentUserId(userId: String) {
        prefs.edit().apply {
            putString(USER_ID, userId)
        }.apply()
    }

    override fun removeCurrentUserId() {
        prefs.edit().apply {
            remove(USER_ID)
        }.apply()
    }

    companion object {
        const val JWT_TOKEN = "jwt_token"
        const val ORDER_ID = "order_id"
        const val USER_ID = "user_id"
    }
}