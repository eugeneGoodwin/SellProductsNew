package com.vn.cashberrymp.presentation.validation.validations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.vortex.soft.sellproductsnew.validation.rules.BaseRule
import com.vortex.soft.sellproductsnew.validation.rules.common.IsCheckedRule
import com.vortex.soft.sellproductsnew.validation.validations.Validation

class BooleanValidation () : Validation() {

    var value: Boolean by mutableStateOf(false)
    private var displayErrors: Boolean by mutableStateOf(false)
    var errorMessage: String = ""

    fun hasError() = displayErrors
    fun enableShowErrors() { displayErrors = true }

    override fun getValidationValue() = if(value) "true" else "false"
    override fun setRulesWithError(rulesError: ArrayList<BaseRule>) {
        rulesError.filter { it.isErrorAvailable }.firstOrNull()?.let { setError(it.getErrorMessage())}
    }

    override fun setError(error: String) {
        errorMessage = error
        enableShowErrors()
    }

    override fun clearError() {
        displayErrors = false
    }

    fun add(isCheckedRule: IsCheckedRule): BooleanValidation {
        super.add(isCheckedRule)
        return this
    }
}