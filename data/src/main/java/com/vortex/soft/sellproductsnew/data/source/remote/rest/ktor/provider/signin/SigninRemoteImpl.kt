package com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.provider.signin

import com.vortex.soft.sellproductsnew.data.repository.signin.source.SigninRemote
import com.vortex.soft.sellproductsnew.data.source.remote.dto.signin.SigninJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.signin.SigninResponseJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.RestCallAdapter
import com.vortex.soft.sellproductsnew.data.source.remote.rest.ktor.adapter.api_login
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType


class SigninRemoteImpl (
    private val restAdapter: RestCallAdapter
) : SigninRemote {
    override suspend fun login(signinDto: SigninJsonDto): Either<FailureType, SigninResponseJsonDto> {
        return restAdapter.make<SigninResponseJsonDto, SigninResponseJsonDto>(
            { api_login(signinDto) }, { it })
    }
}