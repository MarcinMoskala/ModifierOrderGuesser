package com.marcinmoskala.modifier

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.marcinmoskala.composeexercises.ui.samples.guesser.GuesserScreen
import org.jetbrains.compose.resources.painterResource

import modifierorderguesser.composeapp.generated.resources.Res
import modifierorderguesser.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    GuesserScreen()
}