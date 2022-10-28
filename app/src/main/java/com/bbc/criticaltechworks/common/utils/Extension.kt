@file:Suppress("unused")

package com.bbc.criticaltechworks.common.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
import android.text.format.DateUtils
import androidx.biometric.BiometricManager
import androidx.compose.ui.graphics.Color
import coil.request.ImageRequest
import com.bbc.criticaltechworks.appContext
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * This feature determines whether the dark mode is on or off.
 * @param context : Context -> Application context.
 */
fun isDarkMode(context: Context): Boolean {
    val darkModeFlag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return darkModeFlag == Configuration.UI_MODE_NIGHT_YES
}


/**
 * @see Color extension function to apply alpha
 * @param alphaValue : Float -> Alpha value, range from 0.0..1.0
 * @return Alpha applied Color
 */
fun Color.withAlpha(alphaValue: Float = 0f): Color {
    return this.copy(alpha = alphaValue)
}


/**
 * helper method to add scroll fade effect to images
 * @param context : Context -> Application context
 * @param url : String -> Thumbnail url
 * @return ImageRequest
 */
fun scrollFadeImage(context: Context, url: String): ImageRequest {
    return ImageRequest.Builder(context)
        .data(url)
        .crossfade(true)
        .build()
}


/**
 * Converts dp to dp
 * @param dp : Int -> value in dp
 */
fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

/**
 * Converts dp to px
 * @param px : Int -> value in px
 */
fun pxToDp(px: Int): Int {
    return (px / Resources.getSystem().displayMetrics.density).toInt()
}

/**
 * This method returns the device's screen width in pixel
 * @return Screen width in pixel
 */
fun screenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

/**
 * This method returns the device's screen height in pixel
 * @return Screen height in pixel
 */
fun screenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}


/**
 * @see Color extension function to quickly apply aplha
 * @return Alpha applied Color
 */
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


/**
 * Method to launch url in default browser
 * @param context : Context -> Application context
 * @param url : String -> Url to open
 */
fun openBrowser(context: Context, url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(browserIntent)
}


/**
 * Helper method that return time String  in" time ago " format.
 * @param date : Date -> Server returned time in "yyyy-MM-dd'T'HH:mm:ss"
 * @return String -> time in "time ago" format.
 */
@SuppressLint("SimpleDateFormat")
fun getHoursAgo(date: Date): String {
    return try {
        val time: Long = date.time
        val now = System.currentTimeMillis()
        val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
        ago.toString()
    } catch (e: ParseException) {
        e.printStackTrace()
        ""
    }
}


/**
 * This method determines if biometric hardware present and user has enrolled.
 * @return Boolean -> true means biometric is present.
 */
fun biometricPresent(): Boolean {
    return BiometricManager.from(appContext).canAuthenticate(
        BiometricManager.Authenticators.BIOMETRIC_WEAK
    ) == BiometricManager.BIOMETRIC_SUCCESS
}
