package com.marcinmoskala.modifier

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.marcinmoskala.modifier.guesser.trackEvent
import kotlinx.browser.document
import org.w3c.dom.asList

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    trackEvent("app_open")
    document.getElementById("no_wasm_comment")?.remove()
    document.getElementById("spinner")?.remove()
    document.body?.children?.asList()?.forEach { it.remove() }
    ComposeViewport(document.body!!) {
        App()
    }
}