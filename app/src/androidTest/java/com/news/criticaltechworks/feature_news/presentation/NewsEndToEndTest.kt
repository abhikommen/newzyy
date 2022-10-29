package com.news.criticaltechworks.feature_news.presentation

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.test.core.app.ApplicationProvider
import com.news.criticaltechworks.MainActivity
import com.news.criticaltechworks.R
import com.news.criticaltechworks.Routes
import com.news.criticaltechworks.common.utils.CustomNavType
import com.news.criticaltechworks.common.utils.TestTags
import com.news.criticaltechworks.core.util.isVisible
import com.news.criticaltechworks.di.LocalDataModule
import com.news.criticaltechworks.di.RemoteDataModule
import com.news.criticaltechworks.di.RepoModule
import com.news.criticaltechworks.di.UseCaseModule
import com.news.criticaltechworks.feature_news.domain.model.Article
import com.news.criticaltechworks.feature_news.presentation.detail.DetailScreen
import com.news.criticaltechworks.feature_news.presentation.home.HomeScreen
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
class NewsEndAppTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navHostController: NavHostController

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navHostController = rememberNavController()
            NavHost(
                navController = navHostController,
                startDestination = Routes.HOME
            ) {

                composable(route = Routes.HOME) {
                    HomeScreen(navHostController, hiltViewModel())
                }

                composable(
                    route = Routes.DETAIL + "{article}",
                    arguments = listOf(navArgument("article") {
                        type = CustomNavType(Article::class.java)
                    })
                ) {
                    val artistEntity: Article? = it.arguments?.getParcelable("article")
                    if (artistEntity != null) {
                        DetailScreen(
                            artistEntity,
                            navHostController
                        )
                    }
                }

            }
        }
    }

    @Test
    fun bioMetric() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        composeRule.onNodeWithTag(TestTags.LIST_SECTION).onChildren()
            .assertCountEquals(0)

        composeRule.waitUntil(60000) {
            composeRule.onNodeWithTag(TestTags.LIST_SECTION).isVisible()
        }
        composeRule.onNodeWithTag(TestTags.LIST_SECTION).assertHasNoClickAction()

        composeRule.onNode(hasScrollToIndexAction()).performClick()
        composeRule.onNodeWithText(context.getString(R.string.read_more)).assertIsDisplayed()
        composeRule.onNodeWithTag(TestTags.DETAIL_BACK).assertIsDisplayed()
        composeRule.onNodeWithTag(TestTags.DETAIL_BACK).performClick()


    }

}