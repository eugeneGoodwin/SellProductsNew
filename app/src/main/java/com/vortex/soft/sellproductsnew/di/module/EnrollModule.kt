package com.vortex.soft.sellproductsnew.di.module

import com.vortex.soft.sellproductsnew.presentation.enroll.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val enrollModule = module {
    viewModel{ RegistrationViewModel(get(), get()) }
}