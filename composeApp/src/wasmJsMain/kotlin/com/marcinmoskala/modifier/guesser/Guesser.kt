package com.marcinmoskala.composeexercises.ui.samples.guesser

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import modifierorderguesser.composeapp.generated.resources.Res
import modifierorderguesser.composeapp.generated.resources.avatar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun GuesserScreen() {
    var gameState by remember { mutableStateOf<GameState>(Start) }
    val avatar = painterResource(Res.drawable.avatar)

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
            onAnswer = { gameState = onAnswerGiven(state, it, state.question) },
            avatar = avatar,
        )

        is GameOver -> GameOverScreen(
            score = state.score,
            difficulty = state.difficulty,
            onPlayAgain = { gameState = Start }
        )
    }
}
