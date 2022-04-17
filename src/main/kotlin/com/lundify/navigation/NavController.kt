package com.lundify.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.lundify.ui.screens.Screen
import kotlinx.coroutines.delay


class NavController(
    private val startDestination: Screen,
    private var backStackScreens: MutableSet<Screen>,
) {

    var currentScreen: MutableState<Screen> = mutableStateOf(startDestination)

    fun navigateTo(scr: Screen) {
        if (scr != currentScreen.value) {
            if (backStackScreens.contains(currentScreen.value) && currentScreen.value != startDestination) {
                backStackScreens.remove(currentScreen.value)
            }

            if (scr == startDestination) {
                backStackScreens = mutableSetOf()
            } else {
                backStackScreens.add(currentScreen.value)
            }

            currentScreen.value = scr
        }
    }

    fun navigateBack() {
        if (backStackScreens.isNotEmpty()) {
            currentScreen.value = backStackScreens.last()
            backStackScreens.remove(currentScreen.value)
        }
    }

}


@Composable
fun rememberNavController(
    startDestination: Screen,
    backStackScreens: MutableSet<Screen> = mutableSetOf()
): MutableState<NavController> = rememberSaveable {
    mutableStateOf(NavController(startDestination, backStackScreens))
}


class NavigationHost(
    val navController: NavController,
    val contents: @Composable NavigationGraphBuilder.() -> Unit
) {

    @Composable
    fun build() {
        NavigationGraphBuilder().render()
    }

    inner class NavigationGraphBuilder(
        val navController: NavController = this@NavigationHost.navController,
    ) {
        @Composable
        fun render() {
            this@NavigationHost.contents(this)
        }
    }
}


@Composable
fun NavigationHost.NavigationGraphBuilder.composable(
    route: Screen,
    content: @Composable (Modifier) -> Unit
) {
    if (navController.currentScreen.value == route) {

        var timeout by remember {
            mutableStateOf(false)
        }

        val alpha by animateFloatAsState(
            targetValue = if (timeout) 1f else 0f,
            animationSpec = tween(750)
        )

        LaunchedEffect(Unit) {
            delay(100)
            timeout = true
        }

        content(Modifier.alpha(alpha))
    }
}
