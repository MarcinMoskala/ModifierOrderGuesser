package com.marcinmoskala.composeexercises.ui.samples.guesser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun Question(
    answer: List<ModifierOption> = nextQuestion(3).answer,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(20.dp)) {
        Text(buildAnnotatedString {
            append("What should be the output of the following code?\n")
            append("\n")
            appendComposableCall("Box")
            append("(\n")
            appendParameterName("    modifier = ")
            append("Modifier.")
            appendFunctionCall("size")
            append("(")
            appendInt("100")
            append(".")
            appendProperty("dp")
            append("),\n")
            append(") {\n")
            append("    Image(\n")
            appendParameterName("        painter = ")
            appendComposableCall("painterResource")
            append("(")
            appendProperty("id")
            append(" = R.drawable.avatar),\n")
            appendParameterName("        contentDescription = null")
            append(",\n")
            appendParameterName("        modifier = ")
            append("Modifier\n")
            for (m in answer) {
                append("            .")
                append(m.display)
                append("\n")
            }
            append("    )\n")
            append("}")
        }, fontSize = 20.sp)
    }
}