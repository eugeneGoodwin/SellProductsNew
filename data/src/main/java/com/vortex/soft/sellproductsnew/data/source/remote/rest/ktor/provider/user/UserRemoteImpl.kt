package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.provider.user

import com.vortex.soft.sellproductsnew.data.repository.user.source.UserRemote
import com.vortex.soft.sellproductsnew.data.source.remote.dto.user.UserJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.RestCallAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.api_user
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

class UserRemoteImpl (
        private val restAdapter: RestCallAdapter
) : UserRemote {

    override suspend fun getUser(token: String, userId: Int): Either<FailureType, UserJsonDto> {
        return restAdapter.make<UserJsonDto, UserJsonDto>(
            { api_user(token, userId) }, { it })
    }
}