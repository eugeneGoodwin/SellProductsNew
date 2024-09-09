package com.vortex.soft.sellproductsnew.domain.repository

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.signin.SigninDto
import com.vortex.soft.sellproductsnew.domain.dto.signin.SigninResponseDto

interface SigninRepository {
    suspend fun login(signinDto: SigninDto): Either<FailureType, SigninResponseDto>
}