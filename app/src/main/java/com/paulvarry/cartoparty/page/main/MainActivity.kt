package com.paulvarry.cartoparty.page.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.paulvarry.cartoparty.page.home.HomePage
import com.paulvarry.cartoparty.page.map.MapPage
import com.paulvarry.cartoparty.ui.theme.CartoPartyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartoPartyTheme {

                val systemUiController = rememberSystemUiController()
                val useDarkIcons = MaterialTheme.colors.isLight

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons
                    )
                }

                ProvideWindowInsets {
                    BuildNav()
                }
            }
        }
    }

    @Composable
    fun BuildNav() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Route.Home.path) {
            composable(Route.Home.path) { HomePage(navController) }
            composable(
                Route.Map.path + "/{slug}",
                arguments = listOf(navArgument("slug") { type = NavType.StringType })
            ) { backStackEntry ->
                val slug = backStackEntry.arguments?.getString("slug")
                MapPage(navController, slug ?: "main")
            }
        }
    }

    enum class Route(val path: String) {
        Home("home"),
        Map("map"),
    }
}
