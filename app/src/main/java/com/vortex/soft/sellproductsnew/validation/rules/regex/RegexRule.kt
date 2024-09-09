package com.vortex.soft.sellproductsnew.validation.rules.regex

import com.vortex.soft.sellproductsnew.validation.rules.BaseRule


open class RegexRule : BaseRule {

    private var regex: String

    constructor(regex: String) : super("Does not match regex rule") {
        this.regex = regex
    }

    constructor(regex: String, errorMessage: String) : super(errorMessage) {
        this.regex = regex
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return value.matches(regex.toRegex())
        }
    }
}