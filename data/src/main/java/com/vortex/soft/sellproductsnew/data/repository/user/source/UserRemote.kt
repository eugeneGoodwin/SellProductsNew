package com.vortex.soft.sellproductsnew.data.repository.user.source

import com.vortex.soft.sellproductsnew.data.source.remote.dto.user.UserJsonDto
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

interface UserRemote {
    suspend fun getUser(token: String, userId: Int): Either<FailureType, UserJsonDto>
}