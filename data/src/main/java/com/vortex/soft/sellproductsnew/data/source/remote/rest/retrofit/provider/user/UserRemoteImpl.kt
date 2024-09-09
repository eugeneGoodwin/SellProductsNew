package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.provider.user

import com.vortex.soft.sellproductsnew.data.repository.user.source.UserRemote
import com.vortex.soft.sellproductsnew.data.source.remote.dto.user.UserJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.RestAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.SellProductsAPI
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

class UserRemoteImpl (
    private val apiService: SellProductsAPI,
    private val restAdapter: RestAdapter
) : UserRemote {

    override suspend fun getUser(token: String, userId: Int): Either<FailureType, UserJsonDto> {
        return restAdapter.make(apiService.getUser(token, userId), { it })
    }
}