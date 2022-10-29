package com.news.criticaltechworks.feature_news.presentation.detail

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import com.news.criticaltechworks.MainActivity
import com.news.criticaltechworks.ui.theme.CriticalTechWorksTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenKtTest {


    @get: Rule
     val composeTestRule = createAndroidComposeRule<MainActivity>()

    lateinit var navHostController: NavHostController

    @Before
    fun setUp() {

    }

    @Test
    fun myTest(){
        // Start the app
        composeTestRule.setContent {
            CriticalTechWorksTheme {
              //  DetailScreen(article = , navHostController = )
            }
        }
    }


}