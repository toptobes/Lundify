@file:Suppress("WrapUnaryOperator")

package com.lundify.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerMoveFilter as hoverable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lundify.navigation.NavController
import com.lundify.ui.mainelements.Background
import com.lundify.ui.mainelements.RotatingLundifyLogo
import com.lundify.ui.screens.Screen

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(modifier: Modifier, navController: NavController) {
    Background(navController)

    Box(
        modifier = modifier.fillMaxSize().padding(40.dp),
        contentAlignment = Alignment.CenterStart
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        val greenColor = remember { mutableStateOf(Color(34, 197, 94, 150)) }

        Column {
            LoginText(navController, greenColor.value)

            /*--------------------------*/Spacer(Modifier.height(10.dp))

            UserNameTextField(username)

            /*--------------------------*/Spacer(Modifier.height(10.dp))

            PasswordTextField(password)

            /*--------------------------*/Spacer(Modifier.height(20.dp))

            LoginButton(username, password, navController, greenColor)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun LoginText(navController: NavController, greenColor: Color) {
    Row {
        RotatingLundifyLogo(
            size = 67.dp,
            bgColor = greenColor,
            boxModifier = Modifier.offset(y = 6.dp, x = 2.dp),
            logoModifier = Modifier.offset(y = 1.25.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = "Login",
            style = TextStyle(
                fontSize = 40.sp,
                color = greenColor
            )
        )
    }
    Spacer(Modifier.height(5.dp))
    Row {
        Text(
            text = "Please enter your credentials, or ",
            style = TextStyle(
                fontSize = 10.sp,
                color = greenColor
            )
        )
        var createANewAccountColor by remember { mutableStateOf(greenColor) }
        Text(
            text = "create a new account instead",
            style = TextStyle(
                fontSize = 10.sp,
                color = createANewAccountColor,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .clickable {
                    navController.navigateTo(Screen.SignUpScreen)
                }
                .hoverable(
                    onEnter = { createANewAccountColor = Color(44, 102, 219, 255); true },
                    onExit = { createANewAccountColor = greenColor; true }
                )
        )
    }
}

@Composable
private fun LoginButton(
    username: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    navController: NavController,
    greenColor: MutableState<Color>
) {
    val isValid = username.isValidUserName() && password.isValidPassword()
            && username.value.text.isNotEmpty() && password.value.text.isNotEmpty()

    greenColor.value = if (isValid) Color(34, 197, 94, 220) else Color(34, 197, 94, 150)

    Box(
        modifier = Modifier.offset(y = -10.dp, x = 222.dp)
            .size(48.dp, 30.dp)
            .clip(CircleShape)
            .background(greenColor.value)
            .clickable {
                if (isValid) navController.navigateTo(Screen.HomeScreen)
            }
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Filled.ArrowForward,
            contentDescription = "Login",
            tint = Color(255, 255, 255, 200),
            modifier = Modifier.size(48.dp, 30.dp)
        )
    }
}