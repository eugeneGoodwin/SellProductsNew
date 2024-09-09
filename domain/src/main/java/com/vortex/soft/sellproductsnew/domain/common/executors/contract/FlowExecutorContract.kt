package com.vortex.soft.sellproductsnew.domain.common.executors.contract

import com.vortex.soft.sellproductsnew.domain.common.executors.FlowArgument
import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType

interface FlowExecutorContract {

    abstract fun <Input, Output> execute(
        function: FlowArgument<Input, Output>,
        params: Input,
        onResult: suspend (Either<FailureType, Output>) -> Unit = {}
    )

    abstract fun terminate()
}