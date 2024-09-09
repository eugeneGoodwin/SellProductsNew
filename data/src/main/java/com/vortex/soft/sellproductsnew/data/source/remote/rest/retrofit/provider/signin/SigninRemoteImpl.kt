package com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.provider.signin

import com.vortex.soft.sellproductsnew.data.repository.signin.source.SigninRemote
import com.vortex.soft.sellproductsnew.data.source.remote.dto.signin.SigninJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.signin.SigninResponseJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.RestAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.retrofit.common.SellProductsAPI
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType


class SigninRemoteImpl (
    private val apiService: SellProductsAPI,
    private val restAdapter: RestAdapter
) : SigninRemote {

    override suspend fun login(signinDto: SigninJsonDto): Either<FailureType, SigninResponseJsonDto> {
        return restAdapter.make(apiService.login(signinDto), { it })
    }
}