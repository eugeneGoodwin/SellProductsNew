package com.vortex.soft.sellproducts.mainboard.di.module

import com.vortex.soft.sellproducts.mainboard.cart.CartViewModel
import com.vortex.soft.sellproducts.mainboard.catalog.CatalogViewModel
import com.vortex.soft.sellproductsnew.presentation.dashboard.orders.OrdersViewModel
import com.vortex.soft.sellproductsnew.presentation.dashboard.profile.ProfileViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val mainboardModule = module {
    viewModel { CatalogViewModel(get(), get(), get(), get(), get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { OrdersViewModel(get()) }
    viewModel { CartViewModel(get(), get()) }
}