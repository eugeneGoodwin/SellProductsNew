package com.vortex.soft.sellproductsnew.validation.rules.regex

class LatinicRule : RegexRule {

    constructor() : super(LATINIC_REGEX, "Value does not match latinic regex")
    constructor(errorMessage: String) : super(LATINIC_REGEX, errorMessage)

    companion object {
        private const val LATINIC_REGEX = """[-A-Za-z@._]*"""
    }
}
