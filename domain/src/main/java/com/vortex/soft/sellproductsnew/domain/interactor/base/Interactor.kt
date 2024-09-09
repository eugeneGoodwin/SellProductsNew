package com.vortex.soft.sellproductsnew.domain.interactor.base

import com.vortex.soft.sellproductsnew.domain.common.executors.CoroutineAsyncExecutor
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType


/**
 * Abstract class for a Interactor (Use Case in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [Interactor] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */

abstract class Interactor<in Input, out Output> {

    private var executor: CoroutineAsyncExecutor = CoroutineAsyncExecutor()

    abstract suspend fun run(params: Input): Either<FailureType, Output>

    operator fun invoke(params: Input, onResult: (Either<FailureType, Output>) -> Unit = {}) {
        executor.execute<Input, Output>(::run, params, onResult)
    }

    fun unsubscribe() {
        executor.terminate()
    }
}
