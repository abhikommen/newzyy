@file:Suppress("DEPRECATION")

package com.news.criticaltechworks

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.news.criticaltechworks.feature_news.domain.model.Article
import com.news.criticaltechworks.feature_news.presentation.detail.DetailScreen
import com.news.criticaltechworks.feature_news.presentation.home.HomeScreen
import com.news.criticaltechworks.feature_biometric.presentation.splash.SplashScreen
import com.news.criticaltechworks.common.utils.CustomNavType
import com.news.criticaltechworks.common.utils.biometricPresent


/**
 * Object that contains all the Routes(screen) present in the App :) . Route is a String that
 * defines the path to your composable.
 * It is a singleton class so it can be accessed easily from anywhere.
 */
object Routes {
    const val SPLASH = "splash/"
    const val HOME = "home/"
    const val DETAIL = "detail/"
}

/**
 * AppGraph, contains a graph of all the screens. Here we have defined NavHost..
 * The NavHost links the NavController with a navigation graph that specifies
 * the composable destinations that you should be able to navigate between :)
 */
@Composable
fun AppGraph(modifier: Modifier = Modifier) {

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        modifier = modifier,
        startDestination = if (biometricPresent()) Routes.SPLASH else Routes.HOME
    ) {

        composable(route = Routes.SPLASH) {
            SplashScreen(navHostController)
        }

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