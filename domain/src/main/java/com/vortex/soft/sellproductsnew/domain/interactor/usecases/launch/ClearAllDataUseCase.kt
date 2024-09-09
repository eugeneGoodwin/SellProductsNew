package com.vortex.soft.sellproductsnew.domain.interactor.usecases.launch

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import com.vortex.soft.sellproductsnew.domain.common.type.None
import com.vortex.soft.sellproductsnew.domain.interactor.base.Interactor
import com.vortex.soft.sellproductsnew.domain.repository.LaunchRepository

class ClearAllDataUseCase ( private val repository: LaunchRepository
) : Interactor<None, None>() {

    override suspend fun run(params: None): Either<FailureType, None> {
        return repository.clearAllData()
    }
}