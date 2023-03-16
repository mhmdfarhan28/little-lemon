package com.example.littlelemon.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

// Set of Material typography styles to start with
val MarkaziText = FontFamily(
    Font(R.font.markazitext_regular, FontWeight.Normal),
    Font(R.font.markazitext_medium,FontWeight.Medium)
)
val KarlaText = FontFamily(
    Font(R.font.karla_regular, FontWeight.Normal),
    Font(R.font.karla_medium, FontWeight.Medium),
    Font(R.font.karla_bold, FontWeight.Bold)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = MarkaziText,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.em,
        fontSize = 64.sp
    ),
    h2 = TextStyle(
        fontFamily = MarkaziText,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = KarlaText,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    h3 = TextStyle(
        fontFamily = KarlaText,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    h4 = TextStyle(
        fontFamily = KarlaText,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    caption = TextStyle(
        fontFamily = KarlaText,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = KarlaText,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 1.5.em
    ),
    button = TextStyle(
        fontFamily = KarlaText,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)