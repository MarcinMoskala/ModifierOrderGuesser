package com.marcinmoskala.modifier

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.w3c.dom.asList

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    document.getElementById("no_wasm_comment")?.remove()
    document.body?.children?.asList()?.forEach { it.remove() }
    ComposeViewport(document.body!!) {
        App()
    }
}