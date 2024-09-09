package com.vortex.soft.sellproductsnew.validation.rules.regex

class ExpDateCardRule : RegexRule {

    constructor() : super(EXP_DATE_REGEX, "Value does not match expired date regex")
    constructor(errorMessage: String) : super(EXP_DATE_REGEX, errorMessage)

    companion object {
        private const val EXP_DATE_REGEX = "^([0][0-9]|[1][0-2])/([0-9]{2})\$"
    }
}