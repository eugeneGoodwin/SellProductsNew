package com.vortex.soft.sellproductsnew.domain.interactor.usecases.user

import com.vortex.soft.sellproductsnew.domain.interactor.base.SynchronousInteractor
import com.vortex.soft.sellproductsnew.domain.repository.UserRepository

class SetCurrentUserIdUseCase (
    private val repository: UserRepository,
) : SynchronousInteractor<String, Unit>() {

    override fun run(params: String) {
        return repository.setCurrentUserId(params)
    }
}