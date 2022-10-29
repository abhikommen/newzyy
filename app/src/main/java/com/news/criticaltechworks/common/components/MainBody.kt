package com.news.criticaltechworks.common.components

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.news.criticaltechworks.AppGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Main Root Composable of the Application.  Here, the navigation bar is set to black and the
 * status bar is set to translucent to fit the application's aesthetic.
 */
@Composable
fun MainBody() {
    Surface {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController
                .setStatusBarColor(Color.Transparent, darkIcons = true)
            systemUiController.setNavigationBarColor(Color.Black)
        }

        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.navigationBarsPadding(),
        ) {
            AppGraph(Modifier.padding(it))
        }
    }

}