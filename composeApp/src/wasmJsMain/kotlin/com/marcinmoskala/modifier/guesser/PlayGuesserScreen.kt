package com.marcinmoskala.composeexercises.ui.samples.guesser

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview()
@Composable
fun GuesserMobilePreview() {
    PlayGuesserScreen(nextQuestion(3), 10, 1, 2, 4)
}

@OptIn(ExperimentalComposeUiApi::class)
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
    val isDesktop = LocalWindowInfo.current.containerSize.width > 600
    if (isDesktop) {
        PlayGuesserDesktopScreen(question, score, livesUsed, livesLeft, maxVisible, onAnswer, onSkip)
    } else {
        PlayGuesserMobileScreen(question, score, livesUsed, livesLeft, maxVisible, onAnswer, onSkip)
    }
}

@Composable
fun PlayGuesserDesktopScreen(
    question: Question,
    score: Int,
    livesUsed: Int,
    livesLeft: Int,
    maxVisible: Int,
    onAnswer: (List<ModifierOption>) -> Unit = {},
    onSkip: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Hearts(livesUsed, livesLeft)
            Text(
                text = "Score: $score",
                fontSize = 20.sp,
            )
        }
        Question(question.answer, modifier = Modifier.align(Alignment.Center))
        Answers(
            answer = question.answer,
            options = question.allOptions,
            onAnswer = onAnswer,
            maxVisible = maxVisible,
            onSkip = onSkip,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun PlayGuesserMobileScreen(
    question: Question,
    score: Int,
    livesUsed: Int,
    livesLeft: Int,
    maxVisible: Int,
    onAnswer: (List<ModifierOption>) -> Unit = {},
    onSkip: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Hearts(livesUsed, livesLeft)
        Text(
            text = "Score: $score",
            fontSize = 20.sp,
        )
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