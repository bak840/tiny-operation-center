package com.bakulabs.toc.ui.features.flights

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FlightsScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Waiting for a new Raspberry Pi", modifier = Modifier.align(Alignment.Center))
    }
}