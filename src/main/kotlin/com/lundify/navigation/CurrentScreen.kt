package com.lundify.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.lundify.ui.mainelements.NavBarState
import com.lundify.ui.screens.*
import com.lundify.ui.screens.home.HomeScreen
import com.lundify.ui.screens.login.LoginScreen
import com.lundify.ui.screens.login.SignUpScreen

@ExperimentalComposeUiApi
@Composable
fun CurrentScreen(navController: NavController) {

    NavigationHost(navController) {

        composable(Screen.SplashScreen) {
            SplashScreen(it, navController)
        }

        composable(Screen.LoginScreen) {
            LoginScreen(it, navController)
        }

        composable(Screen.SignUpScreen) {
            SignUpScreen(it, navController)
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
