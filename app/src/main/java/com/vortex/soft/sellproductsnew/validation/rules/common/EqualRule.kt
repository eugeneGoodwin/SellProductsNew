package com.vortex.soft.sellproductsnew.validation.rules.common

import com.vortex.soft.sellproductsnew.validation.rules.BaseRule

class EqualRule : BaseRule {

    private var keyword: String

    constructor(keyword: String) :
            super(String.format( "Value does not equal to '%s'", keyword)) {
        this.keyword = keyword
    }

    constructor(keyword: String, errorMessage: String) :
            super(errorMessage) {
        this.keyword = keyword
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value == keyword
        }
    }
}