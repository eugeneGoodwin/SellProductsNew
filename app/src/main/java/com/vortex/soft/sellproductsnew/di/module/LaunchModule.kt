package com.vortex.soft.sellproducts.enter.launch.di

import com.vortex.soft.sellproductsnew.presentation.launch.LaunchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val launchModule = module {
    viewModel{ LaunchViewModel(get()) }
}