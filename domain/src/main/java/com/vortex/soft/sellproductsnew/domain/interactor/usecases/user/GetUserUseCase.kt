package com.vortex.soft.sellproductsnew.domain.interactor.usecases.user

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.dto.user.UserDto
import com.vortex.soft.sellproductsnew.domain.interactor.base.Interactor
import com.vortex.soft.sellproductsnew.domain.repository.UserRepository

class GetUserUseCase( private val repository: UserRepository
) : Interactor<Int, UserDto>() {

    override suspend fun run(params: Int): Either<FailureType, UserDto> {
        return repository.getUser(params)
    }
}