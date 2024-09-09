package com.vortex.soft.sellproductsnew.di.component

import com.vortex.soft.sellproducts.enter.launch.di.launchModule
import com.vortex.soft.sellproductsnew.di.module.enrollModule
import com.vortex.soft.sellproductsnew.di.module.loginModule

val enterComponent = listOf(launchModule, loginModule, enrollModule)