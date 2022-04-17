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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.input.pointer.pointerMoveFilter as hoverable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lundify.navigation.NavController
import com.lundify.ui.mainelements.Background
import com.lundify.ui.mainelements.RotatingLundifyLogo
import com.lundify.ui.screens.Screen

@ExperimentalComposeUiApi
@Composable
fun SignUpScreen(modifier: Modifier, navController: NavController) {
    Background(navController)

    Box(
        modifier = modifier.fillMaxSize().padding(40.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }
        val dateOfBirth = remember { mutableStateOf(TextFieldValue()) }
        val favoriteArtist = remember { mutableStateOf(TextFieldValue()) }
        val favoriteGenre = remember { mutableStateOf(TextFieldValue()) }

        val greenColor = remember { mutableStateOf(Color(34, 197, 94, 150)) }

        Column {
            SignUpText(navController, greenColor.value)

            /*--------------------------*/Spacer(Modifier.height(5.dp))

            UserNameTextField(username)

            /*--------------------------*/Spacer(Modifier.height(10.dp))

            PasswordTextField(password)

            /*--------------------------*/Spacer(Modifier.height(10.dp))

            DateOfBirthTextField(dateOfBirth)

            /*--------------------------*/Spacer(Modifier.height(10.dp))

            FavoriteXTextField(favoriteArtist, "Favorite Artist")

            /*--------------------------*/Spacer(Modifier.height(10.dp))

            FavoriteXTextField(favoriteGenre, "Favorite Genre")

            /*--------------------------*/Spacer(Modifier.height(20.dp))

            SignUpButton(username, password, dateOfBirth, favoriteArtist, favoriteGenre, navController, greenColor)
        }
    }
}

@Composable
fun DateOfBirthTextField(dateOfBirth: MutableState<TextFieldValue>) {
    LoginTextField(
        modifier = Modifier.height(50.dp),
        value = dateOfBirth.value,
        onValueChange = { if (dateOfBirth.value.text.length <= 8) dateOfBirth.value = it },
        label = {
            Text(
                text = if (dateOfBirth.isValidDOB()) "Date of Birth (MM/DD/YYYY)" else "Password (Please enter a valid date)",
                modifier = Modifier.absoluteOffset(y = 6.dp)
            )
        },
        leadingIcon = {
            LoginTextFieldIcon(
                icon = Icons.Filled.DateRange,
            )
        },
        isValid = dateOfBirth.isValidDOB(),
        visualTransformation = {
            displayAsDate(it)
        }
    )
}


private fun MutableState<TextFieldValue>.isValidDOB(): Boolean {
    return value.text.isEmpty() || value.text.matches(Regex("^(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])(19|20)\\d{2}$"))
}


fun displayAsDate(text: AnnotatedString): TransformedText {
    val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 2 == 1 && i < 4) out += "/"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 3) return offset + 1
            if (offset <= 8) return offset + 2
            return 10
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 5) return offset - 1
            if (offset <= 10) return offset - 2
            return 8
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}

@Composable
fun FavoriteXTextField(favorite: MutableState<TextFieldValue>, x: String) {
    LoginTextField(
        modifier = Modifier.height(50.dp),
        value = favorite.value,
        onValueChange = { favorite.value = it },
        label = {
            Text(
                text = x,
                modifier = Modifier.absoluteOffset(y = 6.dp)
            )
        },
        leadingIcon = {
            LoginTextFieldIcon(
                icon = Icons.Filled.FavoriteBorder,
            )
        },
        isValid = true
    )
}


@ExperimentalComposeUiApi
@Composable
private fun SignUpText(navController: NavController, greenColor: Color) {
    Row {
        RotatingLundifyLogo(
            size = 67.dp,
            bgColor = greenColor,
            boxModifier = Modifier.offset(y = 6.dp, x = 2.dp),
            logoModifier = Modifier.offset(y = 1.25.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = "Sign Up",
            style = TextStyle(
                fontSize = 40.sp,
                color = greenColor,
            )
        )
    }
    Spacer(Modifier.height(5.dp))
    Row {
        Text(
            text = "Please enter the required credentials, or ",
            style = TextStyle(
                fontSize = 10.sp,
                color = greenColor
            )
        )
        var logInColor by remember { mutableStateOf(greenColor) }
        Text(
            text = "log in instead",
            style = TextStyle(
                fontSize = 10.sp,
                color = logInColor,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .clickable {
                    navController.navigateTo(Screen.LoginScreen)
                }
                .hoverable(
                    onEnter = { logInColor = Color(44, 102, 219, 255); true },
                    onExit = { logInColor = greenColor; true }
                )
        )
    }
}

@Composable
private fun SignUpButton(
    username: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    dateOfBirth: MutableState<TextFieldValue>,
    favoriteArtist: MutableState<TextFieldValue>,
    favoriteGenre: MutableState<TextFieldValue>,
    navController: NavController,
    greenColor: MutableState<Color>
) {
    val isValid = username.isValidUserName() && password.isValidPassword() && dateOfBirth.isValidDOB()
            && username.value.text.isNotEmpty() && password.value.text.isNotEmpty() && dateOfBirth.value.text.isNotEmpty()
            && favoriteArtist.value.text.isNotEmpty() && favoriteGenre.value.text.isNotEmpty()

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
            contentDescription = "Sign Up",
            tint = Color(255, 255, 255, 200),
            modifier = Modifier.size(48.dp, 30.dp)
        )
    }
}