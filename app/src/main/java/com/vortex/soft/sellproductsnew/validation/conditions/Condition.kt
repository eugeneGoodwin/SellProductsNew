package com.vortex.soft.sellproductsnew.validation.conditions

import com.vortex.soft.sellproductsnew.validation.interfaces.ErrorMessage
import com.vortex.soft.sellproductsnew.validation.interfaces.Validate
import com.vortex.soft.sellproductsnew.validation.rules.BaseRule

abstract class Condition : Validate, ErrorMessage {

    private var errorString: String = "Invalid input"
    val baseRules: MutableList<BaseRule> = ArrayList()

    constructor()

    constructor(errorString: String) {
        this.errorString = errorString
    }

    override fun getErrorMessage(): String {
        return errorString
    }

    fun add(baseRule: BaseRule): Condition {
        baseRules.add(baseRule)
        return this
    }

    fun addAll(baseRules: List<BaseRule>): Condition {
        this.baseRules.addAll(baseRules)
        return this
    }
}