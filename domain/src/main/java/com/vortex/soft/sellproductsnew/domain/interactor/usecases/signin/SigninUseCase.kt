package com.vortex.soft.sellproductsnew.domain.interactor.usecases.signin

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.signin.SigninDto
import com.vortex.soft.sellproductsnew.domain.dto.signin.SigninResponseDto
import com.vortex.soft.sellproductsnew.domain.interactor.base.Interactor
import com.vortex.soft.sellproductsnew.domain.repository.SigninRepository

class SigninUseCase( private val repository: SigninRepository
) : Interactor<SigninDto, SigninResponseDto>() {

    override suspend fun run(params: SigninDto): Either<FailureType, SigninResponseDto> {
        return repository.login(params)
    }
}