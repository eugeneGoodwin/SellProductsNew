package com.vortex.soft.sellproductsnew.validation.validations

import com.vortex.soft.sellproductsnew.validation.rules.BaseRule

class LambdaValidation(val lambda: () -> String, val errorLambda: (String) -> Unit) : Validation() {

    override fun getValidationValue() = lambda()

    override fun setRulesWithError(rulesError: ArrayList<BaseRule>) {
        rulesError.filter { it.isErrorAvailable }.firstOrNull()?.let { setError(it.getErrorMessage())}
    }

    override fun setError(error: String) = errorLambda(error)
    override fun clearError() {}
}