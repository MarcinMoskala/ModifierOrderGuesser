package com.marcinmoskala.composeexercises.ui.samples.guesser

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.unit.dp
import modifierorderguesser.composeapp.generated.resources.Res
import modifierorderguesser.composeapp.generated.resources.avatar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun Answers(
    answer: List<ModifierOption> = nextQuestion(3).answer,
    options: List<List<ModifierOption>> = nextQuestion(3).allOptions,
    maxVisible: Int = 4,
    onAnswer: (List<ModifierOption>) -> Unit = {},
    onSkip: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var optionsDisplayed by remember(options) { mutableStateOf(options) }
    val graphicsLayers = options.associateWith { rememberGraphicsLayer() } // Is this correct?
    LaunchedEffect(options) {
        optionsDisplayed = visuallyUniqueIncludingCorrect(answer, optionsDisplayed, graphicsLayers, maxVisible)
        if (optionsDisplayed.size == 1) {
            onSkip()
        }
    }
    var answerGiven by remember(options) { mutableStateOf<List<ModifierOption>?>(null) }
    FlowRow(
        modifier = modifier
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center,
    ) {
        optionsDisplayed.forEach { option ->
            key(option.toString()) {
                AnswerOption(option, modifier = Modifier
                    .drawWithContent {
                        graphicsLayers[option]?.let { layer ->
                            layer.record { this@drawWithContent.drawContent() }
                            drawLayer(layer)
                        }
                    }
                    .padding(8.dp)
                    .let {
                        when {
                            answerGiven == null -> it
                            option == answer -> it.border(4.dp, Color.Green)
                            option == answerGiven -> it.border(4.dp, Color.Red)
                            else -> it
                        }
                    }
                    .clickable {
                        if (answerGiven == null) {
                            answerGiven = option
                        } else {
                            onAnswer(answerGiven!!)
                        }
                    })
            }
        }
    }
}

suspend fun visuallyUniqueIncludingCorrect(
    correctOption: List<ModifierOption>,
    optionsDisplayed: List<List<ModifierOption>>,
    graphicsLayers: Map<List<ModifierOption>, GraphicsLayer>,
    maxVisible: Int
): List<List<ModifierOption>> {
    val bitmaps = graphicsLayers.mapValues { it.value.toImageBitmap() }
    val correctBitmap = bitmaps[correctOption]!!
    val otherUniqueBitmaps = optionsDisplayed
        .filter { it != correctOption && bitmaps[it] != correctBitmap }
        .distinctBy { bitmaps[it] }
        .take(maxVisible - 1)
        .toSet()
    return optionsDisplayed.filter { it == correctOption || it in otherUniqueBitmaps }
}

@Composable
fun AnswerOption(option: List<ModifierOption>, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(100.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.avatar),
            contentDescription = null,
            modifier = option.fold(Modifier as Modifier) { acc, modifierOption ->
                modifierOption.transformation(acc)
            }
        )
    }
}