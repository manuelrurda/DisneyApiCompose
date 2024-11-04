package com.manuelrurda.ejercicio2cm.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.manuelrurda.ejercicio2cm.R

val MouseMemoirFontFamily = FontFamily(
    Font(R.font.mousememoirs_regular, FontWeight.Normal),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val CharacterHeadingTextStyle = TextStyle(
    fontFamily = MouseMemoirFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 30.sp
)

val HeadingTextStyle = TextStyle(
    fontFamily = MouseMemoirFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 25.sp
)

val SubHeadingTextStyle = TextStyle(
    fontFamily = MouseMemoirFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp
)