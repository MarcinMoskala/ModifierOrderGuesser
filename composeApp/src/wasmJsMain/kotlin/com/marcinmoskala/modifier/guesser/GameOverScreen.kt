package com.marcinmoskala.composeexercises.ui.samples.guesser

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import modifierorderguesser.composeapp.generated.resources.Res
import modifierorderguesser.composeapp.generated.resources.heart_full
import org.jetbrains.compose.resources.imageResource

@Preview
@Composable
fun GameOverScreen(
    score: Int = 10,
    difficulty: Difficulty = Difficulty.Easy,
    onPlayAgain: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "Game over! ",
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            modifier = Modifier.padding(20.dp),
        )
        Text(
            "Your score is $score in $difficulty difficulty.",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.padding(20.dp),
        )
        Text(
            remember(score, difficulty) { getComment(score, difficulty) },
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.padding(20.dp),
        )
        Button(onPlayAgain) {
            Text(
                "Play again",
                fontSize = 24.sp,
            )
        }
        Text(
            buildAnnotatedString {
                val hyperlinkStyle = SpanStyle(
                    color = Color(0xff64B5F6),
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.Underline
                )
                append("Made with ")
                appendInlineContent(id = "love")
                append("\nby ")
                withLink(LinkAnnotation.Url(url = "https://kt.academy/user/marcinmoskala")) {
                    withStyle(style = hyperlinkStyle) {
                        append("Marcin Moskała")
                    }
                }
                append("\nin ")
                withLink(LinkAnnotation.Url(url = "https://www.jetbrains.com/compose-multiplatform/")) {
                    withStyle(style = hyperlinkStyle) {
                        append("Compose Multiplatform")
                    }
                }
                append(".\n\nIf you want to improve your compose skills,\ncheck out ")
                withLink(LinkAnnotation.Url(url = "https://kt.academy/#workshops-offer")) {
                    withStyle(style = hyperlinkStyle) {
                        append("my workshops")
                    }
                }
                append(".")
            },
            inlineContent = mapOf(
                "love" to InlineTextContent(
                    Placeholder(16.sp, 16.sp, PlaceholderVerticalAlign.TextCenter)
                ) {
                    Image(
                        imageResource(Res.drawable.heart_full),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "love"
                    )
                }
            ),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.padding(20.dp),
        )
    }
}

fun getComment(score: Int, difficulty: Difficulty): String {
    val weightedScore = score * difficulty.weight
    return when {
        weightedScore < 5 -> "There is clearly a space for improvement."
        weightedScore < 10 -> "Ok score, I guess"
        weightedScore < 20 -> "Good job!"
        weightedScore < 40 -> "That is an impressive score!"
        else -> "Congratulations! You are a true master of modifiers!"
    }
}