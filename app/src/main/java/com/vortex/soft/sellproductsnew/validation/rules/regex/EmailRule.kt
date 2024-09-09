package com.vortex.soft.sellproductsnew.validation.rules.regex

class EmailRule : RegexRule {

    constructor() : super(EMAIL_REGEX, "Value does not match email regex")
    constructor(errorMessage: String) : super(EMAIL_REGEX, errorMessage)

    /**
        Checking for compliance with the format of the entered address.
        Set a condition for entering an email address:
        The username can contain:

       - Latin alphabet,
       - numbers,
       - signs

        ! # $% & '* + - / =? ^ _ `{| } ~

        point, except for the first and last character, and there cannot be multiple consecutive points

        Full execution methodology: https://en.wikipedia.org/wiki/Email_address#Local-part
        Additional verification:

        the presence of a dot in the domain part of the email, and at least 2 characters after the dot in the domain part.

        limiting the number of characters in the email address (maximum 255 inclusive (added HP, update by comment) characters)
     */
    companion object {
        private const val EMAIL_REGEX =
            "^(?!\\.)(?!.*\\.\\.)[a-zA-Z0-9.!#\$%&'*+\\/=?^_‘{|}~-]{1,64}@[a-zA-Z0-9.-]{1,125}\\.[a-zA-Z]{2,63}(?<!\\.)\$"
    }
}