package com.vortex.soft.sellproductsnew.validation.rules.regex

class PhoneRule : RegexRule {

    constructor() : super(PHONE_REGEX, "Value does not match phone regex")
    constructor(errorMessage: String) : super(PHONE_REGEX, errorMessage)

    companion object {
        private const val PHONE_REGEX = "^(\\+[0-9]{3}){1}\\s*\\([0-9]{2}\\)\\s*([0-9]{3})-([0-9]{2})-([0-9]{2})\$"
    }
}