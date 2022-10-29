package com.news.criticaltechworks

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.news.criticaltechworks.common.components.MainBody
import com.news.criticaltechworks.ui.theme.CriticalTechWorksTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * This application follows single activity architecture. It is an architecture that must consist
 * of one Activity or in some cases relatively small number of activities.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CriticalTechWorksTheme {
                MainBody()
            }
        }
    }
}

