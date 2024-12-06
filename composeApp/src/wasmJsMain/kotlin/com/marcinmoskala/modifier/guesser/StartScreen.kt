package com.marcinmoskala.composeexercises.ui.samples.guesser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun StartScreen(onStart: (Difficulty) -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                "Do you think you understand the order of modifiers in Compose? Let's check it out!",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier.padding(20.dp),
            )
            Text(
                "Choose difficulty:",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            )
            Difficulty.entries.forEach {
                Button(
                    onClick = { onStart(it) },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        it.name,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.defaultMinSize(100.dp)
                    )
                }
            }
        }
    }
}