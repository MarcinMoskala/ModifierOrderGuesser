package com.marcinmoskala.composeexercises.ui.samples.guesser

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun GuesserScreen() {
    var gameState by remember { mutableStateOf<GameState>(Start) }

    when (val state = gameState) {
        is Start -> StartScreen(
            onStart = { gameState = start(it) }
        )

        is Playing -> PlayGuesserScreen(
            question = state.question,
            score = state.score,
            livesUsed = state.livesUsed,
            livesLeft = state.livesLeft,
            maxVisible = state.difficulty.possibilities,
            onSkip = { gameState = onSkip(state) },
            onAnswer = { gameState = onAnswerGiven(state, it, state.question) })

        is GameOver -> GameOverScreen(
            score = state.score,
            difficulty = state.difficulty,
            onPlayAgain = { gameState = Start }
        )
    }
}
