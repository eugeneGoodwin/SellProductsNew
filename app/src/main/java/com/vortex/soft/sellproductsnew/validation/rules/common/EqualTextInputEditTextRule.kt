package com.vortex.soft.sellproductsnew.validation.rules.common

import androidx.appcompat.widget.AppCompatEditText
import com.vortex.soft.sellproductsnew.validation.rules.BaseRule

class EqualTextInputEditTextRule : BaseRule {

    private var textInput: AppCompatEditText

    constructor(textInputParam: AppCompatEditText) :
            super(String.format( "Value does not equal to '%s'", textInputParam.text.toString())) {
        textInput = textInputParam
    }

    constructor(textInputParam: AppCompatEditText, errorMessage: String) :
            super(errorMessage) {
        textInput = textInputParam
    }

    override fun validate(value: String?): Boolean {
        if (value == null) {
            throw NullPointerException()
        } else {
            return textInput.text.toString() == value
        }
    }
}