package com.news.criticaltechworks.feature_biometric.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.news.criticaltechworks.R
import com.news.criticaltechworks.Routes
import com.news.criticaltechworks.common.components.VerticalSpacer
import com.news.criticaltechworks.common.utils.appContext
import com.news.criticaltechworks.common.utils.withAlpha
import com.news.criticaltechworks.feature_biometric.domain.util.BiometricStatus
import com.news.criticaltechworks.feature_biometric.domain.util.BiometricUtil

/**
 * Splash Screen : This screen is the launcher screen of the app. If the device has the biometric
 * set up, an authentication prompt will appear. If Biometric is not set up, it will simply go
 * to the HomeScreen.
 */
@Composable
fun SplashScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        SplashScreenBody(navHostController = navHostController)
    }

}

@Composable
fun SplashScreenBody(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val bioMetricStatus = rememberSaveable {
        mutableStateOf(BiometricStatus.BIOMETRIC_IDLE)
    }
    var message by remember {
        mutableStateOf(appContext.getString(R.string.biometric_found_message))
    }

    LaunchedEffect(key1 = true) {
        BiometricUtil.checkIfBioMetricAvailable(context as AppCompatActivity) {
            bioMetricStatus.value = it
        }
    }

    LaunchedEffect(bioMetricStatus.value) {
        when (bioMetricStatus.value) {

            BiometricStatus.BIOMETRIC_IDLE -> {
                message = appContext.getString(R.string.biometric_found_message)
            }

            BiometricStatus.BIOMETRIC_NOT_FOUND -> {
                message = appContext.getString(R.string.biometric_not_found_message)
            }
            BiometricStatus.BIOMETRIC_SUCCESS -> {
                message = appContext.getString(R.string.biometric_success_message)
                navHostController.navigate(Routes.HOME) {
                    this.popUpTo(Routes.SPLASH) {
                        inclusive = true
                    }
                }
            }

            BiometricStatus.BIOMETRIC_FAILED -> {
                message = appContext.getString(R.string.biometric_failed_message)
            }

            BiometricStatus.BIOMETRIC_RETRYING -> {
                message = appContext.getString(R.string.retrying)
            }

        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = R.drawable.fingerprint,
                contentDescription = "fingerprint image",
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Blue.withAlpha(0.02f), shape = CircleShape)
                    .padding(20.dp)
            )
            VerticalSpacer(size = 20)
            Text(
                text = message,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}
