package com.vortex.soft.sellproductsnew.validation

import com.vortex.soft.sellproductsnew.validation.interfaces.ErrorMessage
import com.vortex.soft.sellproductsnew.validation.rules.BaseRule
import com.vortex.soft.sellproductsnew.validation.validations.Validation

fun withValidateCompose(fields: List<Validation>, success: () -> Unit, fail: () -> Unit = {}) {
    val errorsText = ArrayList<String>()

    fields.forEach { it.clearError() }

    val errors: List<Boolean> = fields.map {
        !validate(it.getValidationValue(), it, errorsText)
    }

    if (!errors.contains(true)) success()
    else fail()
}

fun validate(value: String, validation: Validation, errors: ArrayList<String>): Boolean {
    var isCurrentValueValid = validateRules(value, validation, errors)
    if (isCurrentValueValid) {
        isCurrentValueValid = validateConditions(value, validation, errors)
    }

    return isCurrentValueValid
}

fun validateRules(value: String, validation: Validation, errors: ArrayList<String>): Boolean {
    var isValid = true
    val rulesWithErrors = ArrayList<BaseRule>()
    for (baseRule in validation.baseRules) {
        if (!baseRule.validate(value)) {
            addErrorMessage(baseRule, errors)
            addRuleWithError(baseRule, rulesWithErrors)
            isValid = false
        }
    }
    validation.setRulesWithError(rulesWithErrors)
    return isValid
}

fun validateConditions(value: String, validation: Validation, errors: ArrayList<String>): Boolean {
    for (condition in validation.conditions) {
        if (!condition.validate(value)) {
            addErrorMessage(condition, errors)
            validation.setError(condition.getErrorMessage())
            return false
        }
    }
    return true
}

fun addErrorMessage(errorMessage: ErrorMessage, errors: ArrayList<String>) {
    if (errorMessage.isErrorAvailable) {
        val error = errorMessage.getErrorMessage()
        errors.add(error)
    }
}

private fun addRuleWithError(rule: BaseRule, rulesWithErrors: ArrayList<BaseRule>) {
    rulesWithErrors.add(rule)
}