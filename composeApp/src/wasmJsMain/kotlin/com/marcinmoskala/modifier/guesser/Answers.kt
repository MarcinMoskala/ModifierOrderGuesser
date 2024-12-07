package com.marcinmoskala.composeexercises.ui.samples.guesser

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PixelMap
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(20.dp)
    ) {
        if (answerGiven != null) {
            AnswerCommentText(answerGiven, answer)
        }
        FlowRow(
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
}

@Composable
private fun AnswerCommentText(
    answerGiven: List<ModifierOption>?,
    answer: List<ModifierOption>,
) {
    Text(
        buildAnnotatedString {
            append("Your answer was ")
            val isCorrect = answerGiven == answer
            if (isCorrect) {
                withStyle(style = SpanStyle(color = Color.Green, fontWeight = FontWeight.Bold)) {
                    append("correct")
                }
            } else {
                withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                    append("incorrect")
                }
            }
            append(".")
            if (!isCorrect) {
                append(" The correct answer was marked with ")
                withStyle(style = SpanStyle(color = Color.Green, fontWeight = FontWeight.Bold)) {
                    append("green")
                }
                append(".")
            }
            append(" To continue click on any option.")
        },
        fontSize = 24.sp,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}

suspend fun visuallyUniqueIncludingCorrect(
    correctOption: List<ModifierOption>,
    optionsDisplayed: List<List<ModifierOption>>,
    graphicsLayers: Map<List<ModifierOption>, GraphicsLayer>,
    maxVisible: Int
): List<List<ModifierOption>> {
    val bitmaps = graphicsLayers.mapValues { it.value.toImageBitmap().toPixelMap().let(::ComparablePixelMap) }
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

class ComparablePixelMap(val pixelMap: PixelMap, private val allowedDifference: Float = 0.01f) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ComparablePixelMap) return false
        if (pixelMap.width != other.pixelMap.width || pixelMap.height != other.pixelMap.height) {
            return false
        }
        val allowedDifferent = pixelMap.width * pixelMap.height * allowedDifference
        var different = 0
        for (y in 0 until this.pixelMap.height) {
            for (x in 0 until this.pixelMap.width) {
                if (pixelMap[x, y] != other.pixelMap[x, y]) {
                    different++
                    if (different > allowedDifferent) {
                        return false
                    }
                }
            }
        }
        return true
    }

    override fun hashCode(): Int {
        var result = pixelMap.width
        result = 31 * result + pixelMap.height
        for (y in 0 until this.pixelMap.height) {
            for (x in 0 until this.pixelMap.width) {
                result = 31 * result + pixelMap[x, y].value.toInt()
            }
        }
        return result
    }
}