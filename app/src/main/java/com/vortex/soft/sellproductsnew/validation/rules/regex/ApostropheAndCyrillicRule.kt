package com.vortex.soft.sellproductsnew.validation.rules.regex

class ApostropheAndCyrillicRule : RegexRule {

    constructor() : super(APOSTROPHE_CYRILLIC_REGEX, "Value does not match cirillic regex")
    constructor(errorMessage: String) : super(APOSTROPHE_CYRILLIC_REGEX, errorMessage)

    companion object {
        private const val APOSTROPHE_CYRILLIC_REGEX = """[-\u0400-\u04FF']*"""
    }
}