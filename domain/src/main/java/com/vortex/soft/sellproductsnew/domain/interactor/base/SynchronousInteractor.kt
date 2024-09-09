package com.vortex.soft.sellproductsnew.domain.interactor.base

abstract class SynchronousInteractor<in Input, out Output> {
    abstract fun run(params: Input): Output
    operator fun invoke(params: Input): Output = run(params)
}