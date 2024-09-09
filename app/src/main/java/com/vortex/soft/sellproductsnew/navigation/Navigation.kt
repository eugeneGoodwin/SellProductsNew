package com.vortex.soft.sellproductsnew.navigation

sealed class Navigation(val destination: String) {
    object Splash : Navigation("splash")
    object Welcome : Navigation("welcome")
    object Login : Navigation("login")
    object Registration : Navigation("registration")
    object Dashboard : Navigation("dashboard")
}