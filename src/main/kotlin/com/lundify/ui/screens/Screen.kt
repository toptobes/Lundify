package com.lundify.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(
    val label: String,
    val icon: ImageVector,
    val background: Background,
    val primary: Boolean = false,
    val showNavBar: Boolean? = null
) {
    SplashScreen(
        label = "Sploosh!",
        icon = Icons.Filled.Refresh,
        background = Background(path = "background/LightGrayLB.png", color = Color.Black),
        showNavBar = false
    ),
    LoginScreen(
        label = "Login",
        icon = Icons.Filled.AccountCircle,
        background = Background("background/PurpleLB.png", Color.Magenta),
        showNavBar = false
    ),
    SignUpScreen(
        label = "Sign Up",
        icon = Icons.Filled.AccountCircle,
        background = Background("background/PurpleLB.png", Color.Magenta),
        showNavBar = false
    ),
    HomeScreen(
        label = "Home",
        icon = Icons.Filled.Home,
        background = Background("background/GreenLB.png", Color.Green),
        primary = true
    ),
    Playlists(
        label = "Playlists",
        icon = Icons.Filled.List,
        background = Background("background/BlueLB.png", Color.Blue),
        primary = true
    ),
    Library(
        label = "Library",
        icon = Icons.Filled.Search,
        background = Background("background/YellowLB.png", Color.Yellow),
        primary = true
    ),
    Settings(
        label = "Settings",
        icon = Icons.Filled.Settings,
        background = Background("background/CyanLB.png", Color.Cyan),
        primary = true
    );

    data class Background(
        val path: String,
        val color: Color
    )
}