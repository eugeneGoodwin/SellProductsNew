package com.vortex.soft.sellproductsnew.validation.validations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.vortex.soft.sellproductsnew.validation.rules.BaseRule
import com.vortex.soft.sellproductsnew.validation.rules.common.HasDigitRule
import com.vortex.soft.sellproductsnew.validation.rules.common.HasUpperCaseRule
import com.vortex.soft.sellproductsnew.validation.rules.common.MinLengthRule
import com.vortex.soft.sellproductsnew.validation.rules.common.NotEmptyRule
import com.vortex.soft.sellproductsnew.validation.rules.common.HasLowerCaseRule

class PasswordValidation(fieldName: String) : Validation() {

    var value: String by mutableStateOf("")
    var hasDigit: Boolean by mutableStateOf(false)
    var hasMinLength: Boolean by mutableStateOf(false)
    var hasUpperCase: Boolean by mutableStateOf(false)
    var hasLowerCase: Boolean by mutableStateOf(false)

    private var displayErrors: Boolean by mutableStateOf(false)

    var errorMessage: String = ""
    fun hasError() = displayErrors
    fun enableShowErrors() { displayErrors = true }


    init {
        add(NotEmptyRule(getErrorNoEmptyField(fieldName)))
        add(HasDigitRule(getErrorNoFormatField(fieldName)))
        add(MinLengthRule(6, "min 6 symbols"))
        add(HasUpperCaseRule(getErrorNoFormatField(fieldName)))
        add(HasLowerCaseRule(getErrorNoFormatField(fieldName)))
    }

    override fun setRulesWithError(rulesError: ArrayList<BaseRule>) {
        rulesError.filter { it.isErrorAvailable }.firstOrNull()
            ?.let { setError(it.getErrorMessage()) }
        rulesError.forEach { setErrorByRule(it) }
    }

    override fun getValidationValue(): String {
        return value
    }

    override fun setError(error: String) {
        errorMessage = error
        enableShowErrors()
    }

    override fun clearError() {
        hasDigit = true
        hasMinLength = true
        hasUpperCase = true
        hasLowerCase = true
        displayErrors = false
    }

    private fun setErrorByRule(rule: BaseRule) {
        when (rule) {
            is HasDigitRule -> hasDigit = false
            is MinLengthRule -> hasMinLength = false
            is HasUpperCaseRule -> hasUpperCase = false
            is HasLowerCaseRule -> hasLowerCase = false
            else -> {}
        }
    }

    private fun getErrorNoEmptyField(fieldName: String): String {
        val builder = StringBuilder()
        return builder.append("The field ").append(fieldName).append(" must have a value").toString()
    }

    private fun getErrorNoFormatField(fieldName: String): String {
        val builder = StringBuilder()
        return builder.append("The field ").append(fieldName).append(" has an incorrect format").toString()
    }
}
