package com.vortex.soft.sellproductsnew.data.repository.user

import com.vortex.soft.sellproductsnew.data.mapper.user.UserMapper
import com.vortex.soft.sellproductsnew.data.repository.common.PreferencesProvider
import com.vortex.soft.sellproductsnew.data.repository.user.source.UserRemote
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.monad.flatMap
import com.vortex.soft.sellproductsnew.domain.common.monad.map
import com.vortex.soft.sellproductsnew.domain.common.monad.suspendFlatMap
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.user.UserDto
import com.vortex.soft.sellproductsnew.domain.repository.UserRepository

class UserRepositoryImpl(private val remote: UserRemote,
                         private val prefProvider: PreferencesProvider,
                         private val mapper: UserMapper): UserRepository {
    override suspend fun getUser(userId: Int): Either<FailureType, UserDto> {
        return prefProvider.getToken().suspendFlatMap { token ->
            remote.getUser(token, userId).map { mapper.mapJsonToDomain(it) }
        }
    }

    override fun getCurrentUserId(): String {
        return prefProvider.getCurrentUserId()
    }

    override fun setCurrentUserId(userid: String) {
        prefProvider.saveCurrentUserId(userid)
    }
}