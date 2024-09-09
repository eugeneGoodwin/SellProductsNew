package com.vortex.soft.sellproductsnew.domain.common.executors

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext
import com.vortex.soft.sellproductsnew.domain.common.executors.contract.FlowExecutorContract
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

class FlowAsyncExecutor: FlowExecutorContract {

    private val onBackground: CoroutineContext = Dispatchers.IO
    private val onForeground: CoroutineContext = Dispatchers.Main

    private val parentJob: Job = Job()

    private val scope = CoroutineScope(onForeground + parentJob)

    override fun <Input, Output> execute(
        execute: FlowArgument<Input, Output>,
        params: Input,
        onResult: suspend (Either<FailureType, Output>) -> Unit
    ) {
        scope.launch {
            execute(params).flowOn(onBackground)
                .catch{
                    onResult(Either.Left(FailureType.ServerError))
                }
                .collect {
                    onResult(it)
                }
        }
    }

    override fun terminate() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}