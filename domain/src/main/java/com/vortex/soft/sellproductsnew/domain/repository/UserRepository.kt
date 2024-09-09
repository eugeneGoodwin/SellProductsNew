package com.vortex.soft.sellproductsnew.domain.repository

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.user.UserDto

interface UserRepository {
    suspend fun getUser(userId: Int): Either<FailureType, UserDto>
    fun getCurrentUserId(): String
    fun setCurrentUserId(userid: String)
}