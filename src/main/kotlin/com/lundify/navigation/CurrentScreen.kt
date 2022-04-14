package com.lundify.navigation

import androidx.compose.runtime.Composable
import com.lundify.ui.screens.*

@Composable
fun CurrentScreen(
    navController: NavController,
) {
    NavigationHost(navController) {
        composable(Screen.LoginScreen) {
            LoginScreen(it, navController)
        }

        composable(Screen.HomeScreen) {
            HomeScreen(it, navController)
        }

        composable(Screen.Playlists) {
            Playlists(it, navController)
        }

        composable(Screen.Library) {
            Library(it, navController)
        }

        composable(Screen.Settings) {
            Settings(it, navController)
        }
    }.build()
}
