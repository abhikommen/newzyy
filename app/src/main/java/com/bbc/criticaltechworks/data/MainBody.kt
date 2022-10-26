package com.bbc.criticaltechworks.data

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.bbc.criticaltechworks.AppGraph
import com.bbc.criticaltechworks.MainViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainBody(mainViewModel: MainViewModel) {
    ProvideWindowInsets {
        val context = LocalContext.current
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
            AppGraph(mainViewModel)
        }
    }

}