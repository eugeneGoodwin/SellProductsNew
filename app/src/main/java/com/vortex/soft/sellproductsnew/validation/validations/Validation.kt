package com.vortex.soft.sellproductsnew.validation.validations

import com.vortex.soft.sellproductsnew.validation.conditions.Condition
import com.vortex.soft.sellproductsnew.validation.rules.BaseRule

abstract class Validation {

    val baseRules: MutableList<BaseRule> = ArrayList()
    val conditions: MutableList<Condition> = ArrayList()

    abstract fun getValidationValue(): String
    abstract fun setRulesWithError(rulesError: ArrayList<BaseRule>)
    abstract fun setError(error: String)
    abstract fun clearError()

    protected open fun add(baseRule: BaseRule): Validation {
        baseRules.add(baseRule)
        return this
    }

    protected open fun add(condition: Condition): Validation {
        conditions.add(condition)
        return this
    }
}