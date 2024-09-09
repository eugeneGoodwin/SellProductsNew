package com.vortex.soft.sellproductsnew.domain.common.monad


sealed class Either<out L, out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Left<out L>(val failure: L) : Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Right<out R>(val success: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    //handleResult like fold
    fun eitherHandle(functionLeft: (L) -> Any, functionRight: (R) -> Any): Any =
        when (this) {
            is Left -> functionLeft(failure)
            is Right -> functionRight(success)
        }

}

/** The compose operation (compose() method) - composes a new Function instance from
 *  the Function instance it is called on,
 *  and the Function instance passed as parameter to the compose() method.
 *  The Function returned by compose() will first call the Function passed as parameter to compose(),
 *  and then it will call the Function which compose() was called on.
 *  */
fun <A, B, C> ((A) -> B).compose(f: (B) -> C): (A) -> C = {
    f(this(it))
}

/** The map operation creates one output value for each input value */
fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> {
    return this.flatMap(fn.compose(::right))
}


/**
 * The flatMap operation produces an arbitrary number (zero or more) of values for each input value.
 * flatMap operation is the exact opposite of flat operation
 */
fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> {
    return when (this) {
        is Either.Left -> Either.Left(failure)
        is Either.Right -> fn(success)
    }
}

suspend fun <T, L, R> Either<L, R>.suspendFlatMap(fn: suspend (R) -> Either<L, T>): Either<L, T> {
    return when (this) {
        is Either.Left -> Either.Left(failure)
        is Either.Right -> fn(success)
    }
}

/** Produce a new element in the sequence */
fun <L, R> Either<L, R>.onNext(fn: (R) -> Unit): Either<L, R> {
    this.flatMap(fn.compose(::right))
    return this
}





