package com.lundify.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lundify.navigation.NavController
import com.lundify.ui.mainelements.Background

@Composable
fun HomeScreen(modifier: Modifier, navController: NavController) {
    Background(navController)
}