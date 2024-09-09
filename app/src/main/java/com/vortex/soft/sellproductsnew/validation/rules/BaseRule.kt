package com.vortex.soft.sellproductsnew.validation.rules

import com.vortex.soft.sellproductsnew.validation.interfaces.ErrorMessage
import com.vortex.soft.sellproductsnew.validation.interfaces.Validate

abstract class BaseRule : Validate, ErrorMessage {

    private var errorString: String = ""

    constructor()

    constructor(errorString: String) {
        this.errorString = errorString
    }

    override fun getErrorMessage(): String {
        return errorString
    }
}