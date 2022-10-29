package com.news.criticaltechworks.feature_news.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.news.criticaltechworks.Routes
import com.news.criticaltechworks.common.components.HorizontalSpacer
import com.news.criticaltechworks.common.components.VerticalSpacer
import com.news.criticaltechworks.common.utils.fifty
import com.news.criticaltechworks.common.utils.getHoursAgo
import com.news.criticaltechworks.common.utils.scrollFadeImage
import com.news.criticaltechworks.common.utils.ten
import com.news.criticaltechworks.feature_news.domain.model.Article
import com.news.criticaltechworks.ui.theme.CRITICAL_BLUE
import com.news.criticaltechworks.ui.theme.CRITICAL_GRAY
import com.news.criticaltechworks.R


/**
 * List cell that displays headline thumbnail (if present), article title, article description
 * @param article -> Article class object that contains data of a news article.
 * @param navHostController -> NavHostController object that is used to navigate from
 * one screen to another.
 */
@Composable
fun ArticleCell(
    article: Article,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(modifier = modifier
        .fillMaxWidth()
        .clickable {
            navHostController.navigate(Routes.DETAIL + article.toJson())
        }) {
        ArticleMetaData(article)
        VerticalSpacer(size = 10)
        article.urlToImage?.let {
            AsyncImage(
                model = scrollFadeImage(context, article.urlToImage),
                contentDescription = article.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.onBackground.ten())
            )
        }
        VerticalSpacer(size = 16)
        Divider()
    }
}

@Composable
fun ArticleMetaData(article: Article, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
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
    Text(
        text = article.title ?: stringResource(R.string.unknown),
        color = MaterialTheme.colors.onBackground,
        fontSize = 17.sp
    )
    Text(
        text = article.description ?: stringResource(R.string.unknown),
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colors.onBackground.fifty(),
        fontSize = 15.sp
    )
}
