package com.bbc.criticaltechworks.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
import android.text.format.DateUtils
import androidx.biometric.BiometricManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.bbc.criticaltechworks.App
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun String?.capital(): String =
    this?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        ?: "NA"

fun isDarkMode(context: Context): Boolean {
    val darkModeFlag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return darkModeFlag == Configuration.UI_MODE_NIGHT_YES
}


val appContext = App.appContext

fun Color.withAlpha(alphaValue: Float = 0f): Color {
    return this.copy(alpha = alphaValue)
}

@Composable
fun VerticalSpacer(size: Int) {
    Spacer(
        modifier = Modifier
            .height(size.dp)
            .background(Color.Transparent)
    )
}

fun scrollFadeImage(context: Context, url: String): ImageRequest {
    return ImageRequest.Builder(context)
        .data(url)
        .crossfade(true)
        .build()
}

@Composable
fun HorizontalSpacer(size: Int) {
    Spacer(modifier = Modifier.width(size.dp))
}

fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun pxToDp(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
}

fun screenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun screenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}


fun Color.ten() = this.copy(.10f)
fun Color.twenty() = this.copy(.20f)
fun Color.thirty() = this.copy(.30f)
fun Color.forty() = this.copy(.40f)
fun Color.fifty() = this.copy(.50f)
fun Color.sixty() = this.copy(.60f)
fun Color.seventy() = this.copy(.70f)
fun Color.eighty() = this.copy(.80f)
fun Color.ninety() = this.copy(.90f)

var dayOfTheWeek = android.text.format.DateFormat.format("EEEE", Date()) as String // Thursday
var day = android.text.format.DateFormat.format("dd", Date()) as String // 20
var monthString = android.text.format.DateFormat.format("MMM", Date()) as String // Jun
var monthNumber = android.text.format.DateFormat.format("MM", Date()) as String // 06
var year = android.text.format.DateFormat.format("yyyy", Date()) as String // 2013


fun openBrowser(context: Context, url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(browserIntent)
}


@SuppressLint("SimpleDateFormat")
fun getHoursAgo(stringTimeStamp: String): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    sdf.timeZone = TimeZone.getDefault()
    return try {
        val time: Long = sdf.parse(stringTimeStamp)?.time ?: System.currentTimeMillis()
        val now = System.currentTimeMillis()
        val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
        ago.toString()
    } catch (e: ParseException) {
        e.printStackTrace()
        ""
    }
}


fun biometricPresent(): Boolean {
    return BiometricManager.from(appContext).canAuthenticate(
        BiometricManager.Authenticators.BIOMETRIC_WEAK
    ) == BiometricManager.BIOMETRIC_SUCCESS
}
