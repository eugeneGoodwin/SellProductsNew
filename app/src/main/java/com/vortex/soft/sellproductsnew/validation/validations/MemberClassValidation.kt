package com.vortex.soft.sellproductsnew.validation.validations

import com.vortex.soft.sellproductsnew.validation.conditions.Condition
import com.vortex.soft.sellproductsnew.validation.rules.BaseRule
import kotlin.reflect.KProperty1
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.starProjectedType

class MemberClassValidation<T>(val ref: T, val property: KProperty1<T, *>, val errorListener: (String) -> Unit) : Validation() {

    init {
        if(!property.returnType.isSubtypeOf(String::class.starProjectedType)) throw IllegalArgumentException("Return type not String")
    }

    override fun getValidationValue() = property.get(ref) as String

    override fun setRulesWithError(rulesError: ArrayList<BaseRule>) {
        rulesError.filter { it.isErrorAvailable }.firstOrNull()?.let { setError(it.getErrorMessage())}
    }

    override fun setError(error: String) {
        errorListener(error)
    }

    override fun clearError() {
    }

    public override fun add(baseRule: BaseRule): MemberClassValidation<T> { super.add(baseRule); return this }
    public override fun add(condition: Condition): MemberClassValidation<T> { super.add(condition); return this }
}