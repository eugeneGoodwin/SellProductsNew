package com.vortex.soft.sellproductsnew.domain.interactor.usecases.register

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.register.RegisterDto
import com.vortex.soft.sellproductsnew.domain.dto.register.RegisterResponseDto
import com.vortex.soft.sellproductsnew.domain.interactor.base.Interactor
import com.vortex.soft.sellproductsnew.domain.repository.RegisterRepository

class RegisterUseCase ( private val repository: RegisterRepository
) : Interactor<RegisterDto, RegisterResponseDto>() {

    override suspend fun run(params: RegisterDto): Either<FailureType, RegisterResponseDto> {
        return repository.register(params)
    }
}