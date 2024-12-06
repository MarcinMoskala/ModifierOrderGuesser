package com.marcinmoskala.composeexercises.ui.samples.guesser

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp

data class Question(
    val answer: List<ModifierOption>,
    val allOptions: List<List<ModifierOption>>,
)

data class ModifierOption(
    val name: String,
    val display: AnnotatedString,
    val transformation: (Modifier) -> Modifier,
) {
    override fun toString(): String = name
}

val modifiers: List<ModifierOption> = listOf(
    ModifierOption(
        name = "size(30.dp)",
        display = buildAnnotatedString {
            appendFunctionCall("size")
            append("(")
            appendInt("30")
            append(".")
            appendProperty("dp")
            append(")")
        },
        transformation = { it.size(30.dp) }
    ),
    ModifierOption(
        name = "size(70.dp)",
        display = buildAnnotatedString {
            appendFunctionCall("size")
            append("(")
            appendInt("70")
            append(".")
            appendProperty("dp")
            append(")")
        },
        transformation = { it.size(70.dp) }
    ),
    ModifierOption(
        name = "padding(10.dp)",
        display = buildAnnotatedString {
            appendFunctionCall("padding")
            append("(")
            appendInt("10")
            append(".")
            appendProperty("dp")
            append(")")
        },
        transformation = { it.padding(10.dp) }
    ),
    ModifierOption(
        name = "padding(20.dp)",
        display = buildAnnotatedString {
            appendFunctionCall("padding")
            append("(")
            appendInt("20")
            append(".")
            appendProperty("dp")
            append(")")
        },
        transformation = { it.padding(20.dp) }
    ),
    ModifierOption(
        name = "clip(CircleShape)",
        display = buildAnnotatedString {
            appendFunctionCall("clip")
            append("(")
            appendProperty("CircleShape")
            append(")")
        },
        transformation = { it.clip(CircleShape) }
    ),
    ModifierOption(
        name = "border(3.dp, Color.Black)",
        display = buildAnnotatedString {
            appendFunctionCall("border")
            append("(")
            appendInt("3")
            append(".")
            appendProperty("dp")
            append(", ")
            appendProperty("Color")
            append(".")
            appendProperty("Black")
            append(")")
        },
        transformation = { it.border(3.dp, Color.Black) }
    ),
    ModifierOption(
        name = "border(3.dp, Color.Blue)",
        display = buildAnnotatedString {
            appendFunctionCall("border")
            append("(")
            appendInt("3")
            append(".")
            appendProperty("dp")
            append(", ")
            appendProperty("Color")
            append(".")
            appendProperty("Blue")
            append(")")
        },
        transformation = { it.border(3.dp, Color.Blue) }
    ),
    ModifierOption(
        name = "background(Color.Blue)",
        display = buildAnnotatedString {
            appendFunctionCall("background")
            append("(")
            appendProperty("Color")
            append(".")
            appendProperty("Blue")
            append(")")
        },
        transformation = { it.background(Color.Blue) }
    ),
    ModifierOption(
        name = "background(Color.Black)",
        display = buildAnnotatedString {
            appendFunctionCall("background")
            append("(")
            appendProperty("Color")
            append(".")
            appendProperty("Black")
            append(")")
        },
        transformation = { it.background(Color.Black) }
    ),
    ModifierOption(
        name = "fillMaxSize()",
        display = buildAnnotatedString {
            appendFunctionCall("fillMaxSize()")
        },
        transformation = { it.fillMaxSize() }
    ),
)