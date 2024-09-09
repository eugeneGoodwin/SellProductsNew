package com.vortex.soft.sellproductsnew.domain.interactor.base

import com.vortex.soft.sellproductsnew.domain.common.executors.FlowAsyncExecutor
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

import kotlinx.coroutines.flow.Flow

abstract class FlowInteractor<in Input, out Output>  {

    private var executor: FlowAsyncExecutor = FlowAsyncExecutor()

    abstract suspend fun run(params: Input): Flow<Either<FailureType, Output>>

    operator fun invoke(params: Input, onResult: suspend (Either<FailureType, Output>) -> Unit = {}) {
        executor.execute<Input, Output>(::run, params, onResult)
    }

    fun unsubscribe() {
        executor.terminate()
    }
}