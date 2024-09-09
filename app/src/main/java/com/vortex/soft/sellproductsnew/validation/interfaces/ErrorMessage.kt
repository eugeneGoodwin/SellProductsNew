package com.vortex.soft.sellproductsnew.validation.interfaces

interface ErrorMessage {

    val isErrorAvailable: Boolean
        get() = getErrorMessage().isNotEmpty()

    fun getErrorMessage(): String
}