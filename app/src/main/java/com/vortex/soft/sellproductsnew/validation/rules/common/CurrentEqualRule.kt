package com.vortex.soft.sellproductsnew.validation.rules.common

import com.vortex.soft.sellproductsnew.validation.rules.BaseRule

class CurrentEqualRule : BaseRule {

    private var lambdaKeyword: () -> String

    constructor(lambda: () -> String) :
            super(String.format( "Value does not equal to '%s'", lambda())) {
        lambdaKeyword = lambda
    }

    constructor(lambda: () -> String, errorMessage: String) :
            super(errorMessage) {
        lambdaKeyword = lambda
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return lambdaKeyword() == value
        }
    }
}