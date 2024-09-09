package com.vortex.soft.sellproductsnew.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vortex.soft.sellproductsnew.navigation.Navigation
import com.vortex.soft.sellproductsnew.presentation.launch.SplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.vortex.soft.sellproductsnew.presentation.dashboard.BottomNavItem
import com.vortex.soft.sellproductsnew.presentation.dashboard.cart.CartScreen
import com.vortex.soft.sellproductsnew.presentation.dashboard.catalog.CatalogScreen
import com.vortex.soft.sellproductsnew.presentation.dashboard.orders.OrdersScreen
import com.vortex.soft.sellproductsnew.presentation.dashboard.profile.ProfileScreen
import com.vortex.soft.sellproductsnew.presentation.enroll.RegistrationScreen
import com.vortex.soft.sellproductsnew.presentation.launch.WelcomeScreen
import com.vortex.soft.sellproductsnew.presentation.login.LoginScreen

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Navigation.Splash.destination) {
        composable(Navigation.Splash.destination) {
            SplashScreen(onNavigate = { navController.navigate(Navigation.Welcome.destination) })
        }
        composable(Navigation.Welcome.destination) {
            WelcomeScreen(navController)
        }
        composable(Navigation.Login.destination) {
            LoginScreen(navController)
        }
        composable(Navigation.Registration.destination) {
            RegistrationScreen(navController)
        }
        dashboardNavigation(navController)
    }
}

private fun NavGraphBuilder.dashboardNavigation(navController: NavHostController) {
    navigation(startDestination = BottomNavItem.Catalog.route, route = Navigation.Dashboard.destination) {
        composable(BottomNavItem.Catalog.route) {
            CatalogScreen(navController = navController)
        }
        composable(BottomNavItem.Cart.route) {
            CartScreen(navController = navController)
        }
        composable(BottomNavItem.Orders.route) {
            OrdersScreen(navController = navController)
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}