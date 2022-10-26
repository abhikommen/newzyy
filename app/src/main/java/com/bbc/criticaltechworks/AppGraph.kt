package com.bbc.criticaltechworks

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bbc.criticaltechworks.data.local.entity.Article
import com.bbc.criticaltechworks.ui.screen.detail.DetailScreen
import com.bbc.criticaltechworks.ui.screen.home.HomeScreen
import com.bbc.criticaltechworks.ui.screen.splash.SplashScreen
import com.bbc.criticaltechworks.util.CustomNavType
import com.bbc.criticaltechworks.util.biometricPresent


object Routes {
    const val SPLASH = "splash/"
    const val HOME = "home/"
    const val DETAIL = "detail/"
}


@Composable
fun AppGraph(mainViewModel: MainViewModel) {

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = if (biometricPresent()) Routes.SPLASH else Routes.HOME
    ) {

        composable(route = Routes.SPLASH) {
            SplashScreen(navHostController, mainViewModel)
        }

        composable(route = Routes.HOME) {
            HomeScreen(navHostController, mainViewModel)
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
                    navHostController,
                    mainViewModel
                )
            }
        }

    }

}