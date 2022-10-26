package com.bbc.criticaltechworks.ui.screen.home

import android.Manifest
import android.graphics.Paint.Align
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bbc.criticaltechworks.BuildConfig
import com.bbc.criticaltechworks.MainViewModel
import com.bbc.criticaltechworks.ui.theme.CRITICAL_GRAY
import com.bbc.criticaltechworks.util.*

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        HomeScreenBody(navHostController, mainViewModel)
    }

}

@Composable
fun HomeScreenBody(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier
) {

    val articleList = remember {
        mainViewModel.newArticlesState
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
    ) {
        HeaderActionBar(modifier = Modifier.padding(vertical = 20.dp))

        when (val result = articleList.value) {
            is DataState.Error -> Text(text = "⚠️ Error : ${result.exception.message}")
            DataState.Loading -> Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                CircularProgressIndicator(
                    color = CRITICAL_GRAY
                )
            }
            is DataState.Success -> LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(result.data) {
                    ArticleCell(it, navHostController)
                }
            }
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
