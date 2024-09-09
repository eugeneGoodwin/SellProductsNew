package com.vortex.soft.sellproductsnew.domain.common.executors

import com.vortex.soft.sellproductsnew.domain.common.monad.Either
import com.vortex.soft.sellproductsnew.domain.common.type.FailureType
import kotlinx.coroutines.flow.Flow


typealias Arguments<Input, Output> = suspend (params: Input) -> Either<FailureType, Output>

typealias FlowArgument<Input, Output> = suspend (params: Input) -> Flow<Either<FailureType, Output>>