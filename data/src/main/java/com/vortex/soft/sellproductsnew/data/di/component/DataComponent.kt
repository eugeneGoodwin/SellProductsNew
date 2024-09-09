package com.vortex.soft.sellproductsnew.data.di.component

import com.vortex.soft.sellproductsnew.data.di.module.databaseModule
import com.vortex.soft.sellproductsnew.data.di.module.networkModule
import com.vortex.soft.sellproductsnew.data.di.module.repositoryModule

val dataComponent = listOf(networkModule, repositoryModule, databaseModule)