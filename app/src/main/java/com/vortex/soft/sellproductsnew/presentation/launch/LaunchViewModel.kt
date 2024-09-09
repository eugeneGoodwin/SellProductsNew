package com.vortex.soft.sellproductsnew.presentation.launch

import androidx.lifecycle.MutableLiveData
import com.vortex.soft.sellproductsnew.domain.common.type.None
import com.vortex.soft.sellproductsnew.domain.interactor.usecases.launch.ClearAllDataUseCase
import com.vortex.soft.sellproductsnew.presentation.base.BaseViewModel

class LaunchViewModel (val clearAllDataUseCase: ClearAllDataUseCase
) : BaseViewModel() {

    var clearDataLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun clearData() = clearAllDataUseCase(None()) { it.eitherHandle(::handleFailure, ::handleClearData) }

    private fun handleClearData(none: None) {
        clearDataLiveData.value = true
    }
}