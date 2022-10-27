package com.bbc.criticaltechworks.feature_news.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bbc.criticaltechworks.R
import com.bbc.criticaltechworks.common.utils.*
import com.bbc.criticaltechworks.feature_news.domain.model.Article
import com.bbc.criticaltechworks.ui.theme.CRITICAL_BLUE
import com.bbc.criticaltechworks.ui.theme.CRITICAL_GRAY

@Composable
fun DetailScreen(
    article: Article,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        DetailScreenBody(article, navHostController)
    }

}

@Composable
fun DetailScreenBody(
    article: Article,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            HeaderImageAndActions(article, navHostController)
        }
        item {
            DetailMetaBody(article, modifier = Modifier.padding(12.dp))
        }
    }

}

@Composable
fun HeaderImageAndActions(article: Article, navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        article.urlToImage?.let {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = article.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.onBackground.ten())
            )
        }
        ActionBar(
            navHostController, modifier = Modifier
                .statusBarsPadding()
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun ActionBar(navHostController: NavHostController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Icon(Icons.Rounded.ArrowBack,
            contentDescription = "'back button",
            modifier = Modifier
                .size(35.dp)
                .background(Color.White, shape = CircleShape)
                .padding(5.dp)
                .clickable {
                    navHostController.navigateUp()
                }
        )
    }
}

@Composable
fun DetailMetaBody(article: Article, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = article.author ?: stringResource(R.string.unknown),
                color = CRITICAL_BLUE,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(0.5f)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Rounded.Alarm,
                    contentDescription = "",
                    tint = CRITICAL_GRAY,
                    modifier = Modifier.size(15.dp)
                )
                HorizontalSpacer(size = 2)
                Text(
                    text = getHoursAgo(article.publishedAt),
                    color = CRITICAL_GRAY,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                )
            }
        }
        VerticalSpacer(size = 8)
        Text(
            text = article.title ?: stringResource(R.string.unknown),
            color = MaterialTheme.colors.onBackground,
            fontSize = 20.sp
        )
        VerticalSpacer(size = 8)
        Text(
            text = article.description ?: "",
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onBackground.fifty(),
            fontSize = 15.sp
        )
        Text(
            text = buildAnnotatedString {
                append(article.content ?: stringResource(R.string.unknown))
            },
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onBackground.fifty(),
            fontSize = 15.sp
        )
        VerticalSpacer(size = 8)
        ClickableText(
            text = buildAnnotatedString { append(stringResource(R.string.read_more)) },
            style = TextStyle(color = Color.Blue),
            onClick = {
                article.url?.let {
                    openBrowser(context, article.url)
                }
            }, modifier = Modifier.align(Alignment.End)
        )
    }
}
