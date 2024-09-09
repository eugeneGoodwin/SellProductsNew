package com.vortex.soft.sellproductsnew.validation.expression

abstract class Expression(val validateExpression: () -> Boolean) {

    fun validate(): Boolean = validateExpression()
}