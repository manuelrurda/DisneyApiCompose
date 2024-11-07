package com.manuelrurda.ejercicio2cm.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.manuelrurda.ejercicio2cm.ui.theme.HeadingTextStyle

@Composable
fun ErrorCard(message: String, onTap: () -> Unit){
    Box(modifier = Modifier.fillMaxSize()
        .clickable { onTap() }
        .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center){
        Text(text = message,
            style = HeadingTextStyle,
            color = Color.White,
            textAlign = TextAlign.Center)
    }
}