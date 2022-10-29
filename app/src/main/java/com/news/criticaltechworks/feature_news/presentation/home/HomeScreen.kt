package com.news.criticaltechworks.feature_news.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.news.criticaltechworks.BuildConfig
import com.news.criticaltechworks.common.components.VerticalSpacer
import com.news.criticaltechworks.common.utils.TestTags
import com.news.criticaltechworks.common.utils.day
import com.news.criticaltechworks.common.utils.monthString
import com.news.criticaltechworks.common.utils.year
import com.news.criticaltechworks.feature_news.presentation.home.components.ArticleCell
import com.news.criticaltechworks.ui.theme.CRITICAL_GRAY

/**
 * This is the Home screen of the app. User lands to this screen after successfully passing
 * biometric authentication..
1. News provider is being displayed as a screen title
2. Headlines are presented in a list format.
3. Each cell presents the headline title
4. Headlines are sorted by date
5. The user is able to scroll through the list of headlines
6. Each cell presents headline image, if available
 */
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        HomeScreenBody(navHostController, homeViewModel)
    }

}

@Composable
fun HomeScreenBody(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {

    val articleListState = remember {
        homeViewModel.articleListState
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
    ) {
        HeaderActionBar(modifier = Modifier.padding(vertical = 20.dp))

        LazyColumn(
            modifier = Modifier.testTag(TestTags.LIST_SECTION),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(articleListState.value.articleList) {
                ArticleCell(it, navHostController)
            }
        }

        if (articleListState.value.isLoading) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                CircularProgressIndicator(
                    color = CRITICAL_GRAY,
                    strokeWidth = 1.dp
                )
            }
        }

        if (articleListState.value.error.isNotBlank()) {
            Text(
                text = articleListState.value.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
    }
}


@Composable
fun HeaderActionBar(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "$day $monthString $year",
            fontWeight = FontWeight.SemiBold,
            color = CRITICAL_GRAY,
            fontSize = 15.sp
        )
        VerticalSpacer(size = 5)
        Text(
            text = BuildConfig.APP_NAME,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
    }
}
