package com.news.criticaltechworks.core.util

import androidx.compose.ui.test.SemanticsNodeInteraction

fun SemanticsNodeInteraction.isVisible(): Boolean {
    return this.fetchSemanticsNode().size.component2() > 0
}