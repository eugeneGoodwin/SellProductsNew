package com.vortex.soft.sellproductsnew.validation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.vortex.soft.sellproductsnew.validation.conditions.Condition
import com.vortex.soft.sellproductsnew.validation.rules.BaseRule
import com.vortex.soft.sellproductsnew.validation.validations.Validation


open class ComposeValidation(): Validation() {

    var value: String by mutableStateOf("")

    private var displayErrors: Boolean by mutableStateOf(false)

    val errorMessage: String?
        get() = getError()
    private var errorFor: String = ""

    fun hasError() = displayErrors
    fun enableShowErrors() { displayErrors = true }

    private fun getError(): String? {
        return if (hasError()) {
            errorFor
        } else {
            null
        }
    }

    override fun getValidationValue(): String {
        return value
    }

    override fun setRulesWithError(rulesError: ArrayList<BaseRule>) {
        rulesError.filter { it.isErrorAvailable }.firstOrNull()?.let { setError(it.getErrorMessage()) }
    }

    override fun setError(error: String) {
        errorFor = error
        enableShowErrors()
    }

    override fun clearError() {
        displayErrors = false
    }

    public override fun add(baseRule: BaseRule): ComposeValidation { super.add(baseRule); return this }
    public override fun add(condition: Condition): ComposeValidation { super.add(condition); return this }
}