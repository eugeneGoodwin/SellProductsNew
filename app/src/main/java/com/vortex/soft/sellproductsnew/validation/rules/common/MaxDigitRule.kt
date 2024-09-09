package com.vortex.soft.sellproductsnew.validation.common

import com.vortex.soft.sellproductsnew.validation.rules.BaseRule

class MaxDigitRule : BaseRule {

    private var maxDigit: Int = 0

    constructor(max: Int) :
            super(String.format("Value must be more then %s", max)) {
        this.maxDigit = max
    }

    constructor(maxLength: Int, errorMessage: String) :
            super(errorMessage) {
        this.maxDigit = maxLength
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.toIntOrNull()?.let { it <= maxDigit } ?: false
        }
    }
}