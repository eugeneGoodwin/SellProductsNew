package com.vortex.soft.sellproductsnew.validation.rules.regex

class OnlyDigitRule : RegexRule {

    constructor() : super(DIGIT_REGEX, "Value does not match digit regex")
    constructor(errorMessage: String) : super(DIGIT_REGEX, errorMessage)

    companion object {
        private const val DIGIT_REGEX = """[0-9]*"""
    }
}