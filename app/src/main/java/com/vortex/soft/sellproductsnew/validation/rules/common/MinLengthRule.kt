package com.vortex.soft.sellproductsnew.validation.rules.common

import com.vortex.soft.sellproductsnew.validation.rules.BaseRule

class MinLengthRule : BaseRule {

    private var minLength: Int = 0

    constructor(minLength: Int) :
            super(String.format("Length must not less %d characters", minLength)) {
        this.minLength = minLength
    }

    constructor(minLength: Int, errorMessage: String) :
            super(errorMessage) {
        this.minLength = minLength
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.length >= minLength
        }
    }
}