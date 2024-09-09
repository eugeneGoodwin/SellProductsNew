package com.vortex.soft.sellproductsnew.data.repository.signin.source

import com.vortex.soft.sellproductsnew.data.source.remote.dto.signin.SigninJsonDto
import com.vortex.soft.sellproductsnew.data.source.remote.dto.signin.SigninResponseJsonDto
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

interface SigninRemote {
    suspend fun login(signinDto: SigninJsonDto): Either<FailureType, SigninResponseJsonDto>
}