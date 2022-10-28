package com.bbc.criticaltechworks.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Composable use to give Vertical empty Space
 * @param size : Int  --> Space height in dp
 */
@Composable
fun VerticalSpacer(size: Int) {
    Spacer(
        modifier = Modifier
            .height(size.dp)
            .background(Color.Transparent)
    )
}