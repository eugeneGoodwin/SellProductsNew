package com.vortex.soft.sellproductsnew.validation.rules.regex

class AddressCyrillicRule : RegexRule {

    constructor() : super(ADDRESS_CYRILLIC_REGEX, "Value does not match address regex")
    constructor(errorMessage: String) : super(ADDRESS_CYRILLIC_REGEX, errorMessage)

    companion object {
        private const val ADDRESS_CYRILLIC_REGEX = "[-\\u0400-\\u04FF0-9'. /]*"
    }
}