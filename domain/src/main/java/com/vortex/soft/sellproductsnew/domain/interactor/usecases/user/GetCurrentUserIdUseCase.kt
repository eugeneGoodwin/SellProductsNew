package com.vortex.soft.sellproductsnew.domain.interactor.usecases.user

import com.vortex.soft.sellproductsnew.domain.common.type.None
import com.vortex.soft.sellproductsnew.domain.interactor.base.SynchronousInteractor
import com.vortex.soft.sellproductsnew.domain.repository.UserRepository

class GetCurrentUserIdUseCase (
    private val repository: UserRepository,
) : SynchronousInteractor<None, String>() {

    override fun run(params: None): String {
        return repository.getCurrentUserId()
    }
}