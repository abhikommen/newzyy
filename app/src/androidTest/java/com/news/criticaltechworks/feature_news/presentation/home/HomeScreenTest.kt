package com.news.criticaltechworks.feature_news.presentation.home

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.news.criticaltechworks.MainActivity
import com.news.criticaltechworks.Routes
import com.news.criticaltechworks.common.utils.TestTags
import com.news.criticaltechworks.core.util.isVisible
import com.news.criticaltechworks.di.LocalDataModule
import com.news.criticaltechworks.di.RemoteDataModule
import com.news.criticaltechworks.di.RepoModule
import com.news.criticaltechworks.di.UseCaseModule
import com.news.criticaltechworks.ui.theme.CriticalTechWorksTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest()
@UninstallModules(
    LocalDataModule::class,
    RemoteDataModule::class,
    RepoModule::class,
    UseCaseModule::class
)
class HomeScreenTest {

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get : Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()

            CriticalTechWorksTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.HOME
                ) {
                    composable(route = Routes.HOME) {
                        HomeScreen(
                            navHostController = navController,
                            homeViewModel = hiltViewModel()
                        )
                    }
                }
            }
        }
    }


    @Test
    fun scrollArticleList() {
        composeRule.onNodeWithTag(TestTags.LIST_SECTION).onChildren()
            .assertCountEquals(0)

        composeRule.waitUntil {
            composeRule.onNodeWithTag(TestTags.LIST_SECTION).isVisible()
        }
        composeRule.onNodeWithTag(TestTags.LIST_SECTION).assertHasNoClickAction()
    }

}
