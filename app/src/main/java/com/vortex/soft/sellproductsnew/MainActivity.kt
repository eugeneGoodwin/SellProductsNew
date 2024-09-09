package com.vortex.soft.sellproductsnew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vortex.soft.sellproductsnew.navigation.navhost.NavHost
import com.vortex.soft.sellproductsnew.presentation.dashboard.BottomNavItem
import com.vortex.soft.sellproductsnew.presentation.dashboard.BottomNavigationBar
import com.vortex.soft.sellproductsnew.ui.theme.SellProductsNewTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SellProductsNewTheme {
                val navController = rememberNavController()

                val dashboardScreens = listOf(
                    BottomNavItem.Catalog,
                    BottomNavItem.Cart,
                    BottomNavItem.Orders,
                    BottomNavItem.Profile)

                val showBottomBar = currentRoute(navController) in dashboardScreens.map { it.route }

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) {it
                    NavHost(navController)
                }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SellProductsNewTheme {
        Greeting("Android")
    }
}