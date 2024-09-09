package com.vortex.soft.sellproductsnew.presentation.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vortex.soft.sellproductsnew.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val bottomNavigationItems = listOf(
        BottomNavItem.Catalog,
        BottomNavItem.Cart,
        BottomNavItem.Orders,
        BottomNavItem.Profile
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination

        bottomNavigationItems.forEach { item ->
            val isSelected = currentRoute?.hierarchy?.any { it.route == item.route } == true
            val itemIcon = if (isSelected) item.icon else item.selectedIcon

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route)
                    /*navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }*/

                },
                icon = { Icon(painterResource(itemIcon), contentDescription = null, modifier = Modifier.size(28.dp)) },
                label = { Text(
                    text = item.label,
                    style = MaterialTheme.typography.labelSmall)
                }
            )
        }
    }
}

sealed class BottomNavItem(val route: String, val icon: Int, val selectedIcon: Int, val label: String) {
    object Catalog : BottomNavItem(
        "catalog",
        R.drawable.ic_products,
        R.drawable.ic_products_tint,
        "Catalog")
    object Cart : BottomNavItem(
        "cart",
        R.drawable.ic_cart_tint,
        R.drawable.ic_cart,
        "Cart")
    object Orders : BottomNavItem(
        "orders",
        R.drawable.ic_orders_tint,
        R.drawable.ic_orders,
        "Orders")
    object Profile : BottomNavItem(
        "profile",
        R.drawable.ic_user,
        R.drawable.ic_user_tint,
        "Profile")
}