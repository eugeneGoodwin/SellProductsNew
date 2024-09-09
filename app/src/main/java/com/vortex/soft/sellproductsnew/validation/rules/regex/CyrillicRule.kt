package com.vortex.soft.sellproductsnew.validation.rules.regex

class CyrillicRule : RegexRule {

    constructor() : super(CIRILLIC_REGEX, "Value does not match cirillic regex")
    constructor(errorMessage: String) : super(CIRILLIC_REGEX, errorMessage)

    companion object {
        private const val CIRILLIC_REGEX = """[-\u0400-\u04FF']*"""
    }
}