package com.vn.cashberrymp.presentation.validation.rules.regex

import com.vortex.soft.sellproductsnew.validation.rules.regex.RegexRule

class VietnamPhoneRule : RegexRule {

    constructor() : super(PHONE_REGEX, "Value does not match vietnam phone regex")
    constructor(errorMessage: String) : super(PHONE_REGEX, errorMessage)

    companion object {
        private const val PHONE_REGEX = "^\\(0\\)\\s*([0-9]{2})\\s*([0-9]{3})\\s*([0-9]{4})\$"
        private const val PHONE_REGEX_ = "^\\([0-9]{1}\\)\\s*([0-9]{2})\\s*([0-9]{3})\\s*([0-9]{4})\$"
    }
}