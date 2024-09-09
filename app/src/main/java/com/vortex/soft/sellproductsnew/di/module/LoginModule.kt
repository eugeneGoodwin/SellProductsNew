package com.vortex.soft.sellproductsnew.di.module

import com.vortex.soft.sellproductsnew.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel{ LoginViewModel(get(), get()) }
}