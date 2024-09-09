package com.vortex.soft.sellproductsnew.di.component

import com.vortex.soft.sellproducts.mainboard.di.component.mainboardComponent
import com.vortex.soft.sellproductsnew.data.di.component.dataComponent
import com.vortex.soft.sellproductsnew.di.component.enterComponent
import com.vortex.soft.sellproductsnew.domain.di.component.domainComponent

fun <T> concatenate(vararg lists: List<T>): List<T> {
    return listOf(*lists).flatten()
}

val appComponent = concatenate(dataComponent, domainComponent, enterComponent, mainboardComponent)