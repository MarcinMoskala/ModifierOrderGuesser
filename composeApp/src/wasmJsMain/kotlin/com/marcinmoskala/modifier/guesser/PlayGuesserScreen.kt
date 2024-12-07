package com.marcinmoskala.composeexercises.ui.samples.guesser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun PlayGuesserScreen(
    question: Question,
    score: Int,
    livesUsed: Int,
    livesLeft: Int,
    maxVisible: Int,
    onAnswer: (List<ModifierOption>) -> Unit = {},
    onSkip: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Hearts(livesUsed, livesLeft)
            Text(
                text = "Score: $score",
                fontSize = 20.sp,
            )
        }
        Question(question.answer)
        Answers(
            answer = question.answer,
            options = question.allOptions,
            onAnswer = onAnswer,
            maxVisible = maxVisible,
            onSkip = onSkip,
        )
    }
}