package com.vortex.soft.sellproductsnew.validation

import com.vortex.soft.sellproductsnew.validation.interfaces.ErrorMessage
import com.vortex.soft.sellproductsnew.validation.mode.Rule_Mode
import com.vortex.soft.sellproductsnew.validation.mode.Validation_Mode
import com.vortex.soft.sellproductsnew.validation.rules.BaseRule
import com.vortex.soft.sellproductsnew.validation.validations.Validation


class Validator private constructor(val listener: OnValidateListener) {

    private var validationMode = Validation_Mode.ALL_VALIDATIONS
    private var ruleMode = Rule_Mode.ALL_RULES
    private var validations = ArrayList<Validation>()

    interface OnValidateListener {
        fun onValidateSuccess(values: List<String>)
        fun onValidateFailed(errors: List<String>)
    }

    fun setValidationMode(mode: Validation_Mode): Validator {
        this.validationMode = mode
        return this
    }

    fun setRuleMode(mode: Rule_Mode): Validator {
        this.ruleMode = mode
        return this
    }

    fun validate(vararg validations: Validation) {
        var isOverallValid = true
        var isValid = false
        val values = ArrayList<String>()
        val errors = ArrayList<String>()

        this.validations.clear()
        this.validations.addAll(validations)
        clear()

        for (validation in validations) {
            val value = validation.getValidationValue()
            value.let {
                val isCurrentValueValid = validate(it, validation, errors)
                if (isCurrentValueValid) {
                    isValid = true
                    values.add(it)
                } else {
                    isOverallValid = false
                    isValid = false
                }
            }
            if (validationMode == Validation_Mode.ONLY_FIRST_VALIDATION && isValid == false && isOverallValid == false) break
        }
        if (isValid && isOverallValid) listener.onValidateSuccess(values)
        else listener.onValidateFailed(errors)
    }

    fun clear() = clearAllErrors()
    private fun clearAllErrors() =  validations.forEach { it.clearError() }

    private fun validate(value: String, validation: Validation, errors: ArrayList<String>): Boolean {
        var isCurrentValueValid = validateRules(value, validation, errors)
        if (isCurrentValueValid) {
            isCurrentValueValid = validateConditions(value, validation, errors)
        }

        return isCurrentValueValid
    }

    private fun validateRules(value: String, validation: Validation, errors: ArrayList<String>): Boolean {
        var isValid = true
        val rulesWithErrors = ArrayList<BaseRule>()
        for (baseRule in validation.baseRules) {
            if (!baseRule.validate(value)) {
                addErrorMessage(baseRule, errors)
                addRuleWithError(baseRule, rulesWithErrors)
                isValid = false
                if(ruleMode == Rule_Mode.ONLY_FIRST_RULE) break
            }
        }
        validation.setRulesWithError(rulesWithErrors)
        return isValid
    }

    private fun validateConditions(value: String, validation: Validation, errors: ArrayList<String>): Boolean {
        for (condition in validation.conditions) {
            if (!condition.validate(value)) {
                addErrorMessage(condition, errors)
                return false
            }
        }
        return true
    }

    private fun addErrorMessage(errorMessage: ErrorMessage, errors: ArrayList<String>) {
        if (errorMessage.isErrorAvailable) {
            val error = errorMessage.getErrorMessage()
            errors.add(error)
        }
    }

    private fun addRuleWithError(rule: BaseRule, rulesWithErrors: ArrayList<BaseRule>) {
        rulesWithErrors.add(rule)
    }

    companion object {
        fun with(listener: OnValidateListener): Validator = Validator(listener)
    }
}