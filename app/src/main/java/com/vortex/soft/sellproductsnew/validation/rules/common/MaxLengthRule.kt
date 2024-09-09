package com.vortex.soft.sellproductsnew.validation.rules.common

import com.vortex.soft.sellproductsnew.validation.rules.BaseRule

class MaxLengthRule : BaseRule {

    private var maxLength: Int = 0

    constructor(maxLength: Int) :
            super(String.format("Length must not exceed %d characters", maxLength)) {
        this.maxLength = maxLength
    }

    constructor(maxLength: Int, errorMessage: String) :
            super(errorMessage) {
        this.maxLength = maxLength
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.length <= maxLength
        }
    }
}