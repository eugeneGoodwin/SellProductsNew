package com.vortex.soft.sellproductsnew.common

fun String.addJWTPrefix(): String {
    return "Bearer ".plus(this)
}