package com.vortex.soft.sellproductsnew.domain.common.executors


import kotlinx.coroutines.*
import com.vortex.soft.sellproductsnew.domain.common.executors.contract.AsyncExecutorContract
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType


import kotlin.coroutines.CoroutineContext

class CoroutineAsyncExecutor : AsyncExecutorContract {

    private val onBackground: CoroutineContext = Dispatchers.IO
    private val onForeground: CoroutineContext = Dispatchers.Main

    private val parentJob: Job = Job()

    private val scope = CoroutineScope(onForeground + parentJob)

    override fun <Input, Output> execute(
        execute: Arguments<Input, Output>,
        params: Input,
        onResult: (Either<FailureType, Output>) -> Unit
    ) {
        scope.launch {
            val result = withContext(onBackground) {
                execute(params)
            }
            onResult(result)
        }
    }

    override fun terminate() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

}
