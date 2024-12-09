package com.marcinmoskala.composeexercises.ui.samples.guesser

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
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
    val transformation: BoxScope.(Modifier) -> Modifier,
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
            append(", Color.")
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
            append(", Color.")
            appendProperty("Blue")
            append(")")
        },
        transformation = { it.border(3.dp, Color.Blue) }
    ),
    ModifierOption(
        name = "background(Color.Blue)",
        display = buildAnnotatedString {
            appendFunctionCall("background")
            append("(Color.")
            appendProperty("Blue")
            append(")")
        },
        transformation = { it.background(Color.Blue) }
    ),
    ModifierOption(
        name = "background(Color.Black)",
        display = buildAnnotatedString {
            appendFunctionCall("background")
            append("(Color.")
            appendProperty("Black")
            append(")")
        },
        transformation = { it.background(Color.Black) }
    ),
    ModifierOption(
        name = "fillMaxSize()",
        display = buildAnnotatedString {
            appendFunctionCall("fillMaxSize")
            append("()")
        },
        transformation = { it.fillMaxSize() }
    ),
    ModifierOption(
        name = "fillMaxWidth()",
        display = buildAnnotatedString {
            appendFunctionCall("fillMaxWidth")
            append("()")
        },
        transformation = { it.fillMaxWidth() }
    ),
    ModifierOption(
        name = "fillMaxHeight()",
        display = buildAnnotatedString {
            appendFunctionCall("fillMaxHeight()")
        },
        transformation = { it.fillMaxHeight() }
    ),
    ModifierOption(
        name = "offset(10.dp, 10.dp)",
        display = buildAnnotatedString {
            appendFunctionCall("offset")
            append("(")
            appendInt("10")
            append(".")
            appendProperty("dp")
            append(", ")
            appendInt("10")
            append(".")
            appendProperty("dp")
            append(")")
        },
        transformation = { it.offset(10.dp, 10.dp) }
    ),
    ModifierOption(
        name = "offset(20.dp, 20.dp)",
        display = buildAnnotatedString {
            appendFunctionCall("offset")
            append("(")
            appendInt("20")
            append(".")
            appendProperty("dp")
            append(", ")
            appendInt("20")
            append(".")
            appendProperty("dp")
            append(")")
        },
        transformation = { it.offset(20.dp, 20.dp) }
    ),
    ModifierOption(
        name = "align(Alignment.Center)",
        display = buildAnnotatedString {
            appendFunctionCall("align")
            append("(")
            appendProperty("Alignment")
            append(".")
            appendProperty("Center")
            append(")")
        },
        transformation = { it.align(Alignment.Center) }
    ),
    ModifierOption(
        name = "align(Alignment.BottomEnd)",
        display = buildAnnotatedString {
            appendFunctionCall("align")
            append("(")
            appendProperty("Alignment")
            append(".")
            appendProperty("BottomEnd")
            append(")")
        },
        transformation = { it.align(Alignment.BottomEnd) }
    ),
)