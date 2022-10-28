@file:Suppress("DEPRECATION")

package com.bbc.criticaltechworks

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bbc.criticaltechworks.feature_news.domain.model.Article
import com.bbc.criticaltechworks.feature_news.presentation.detail.DetailScreen
import com.bbc.criticaltechworks.feature_news.presentation.home.HomeScreen
import com.bbc.criticaltechworks.feature_biometric.presentation.splash.SplashScreen
import com.bbc.criticaltechworks.common.utils.CustomNavType
import com.bbc.criticaltechworks.common.utils.biometricPresent


object Routes {
    const val SPLASH = "splash/"
    const val HOME = "home/"
    const val DETAIL = "detail/"
}

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