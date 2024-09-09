package com.vortex.soft.sellproductsnew.validation.rules.common

import com.vortex.soft.sellproductsnew.validation.rules.BaseRule


class NotEmptyRule : BaseRule {

    constructor() : super("Value must not be empty")

    constructor(errorMessage: String) : super(errorMessage)

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.isNotEmpty()
        }
    }
}