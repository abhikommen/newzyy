@file:Suppress("DEPRECATION")

package com.news.criticaltechworks.feature_biometric.domain.util

import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.*
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.news.criticaltechworks.R
import com.news.criticaltechworks.common.utils.appContext

/**
 * Simple utility that responds to biometric events and display a prompt accordingly.
 */
object BiometricUtil {

    fun checkIfBioMetricAvailable(
        context: AppCompatActivity,
        success: (biometricStatus: BiometricStatus) -> Unit
    ) {
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                launchBioMetricPrompt(context, success)
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Toast.makeText(
                    context,
                    context.getString(R.string.biometric_na_message),
                    Toast.LENGTH_SHORT
                ).show()
                success(BiometricStatus.BIOMETRIC_NOT_FOUND)
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // Prompts the user to create credentials that your app accepts.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                        putExtra(
                            Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                        )
                    }.also {
                        context.startActivityForResult(it, 1221)
                    }
                }
            }

            else -> {
                // We are simply not interested in other values...
            }
        }

    }


    private fun launchBioMetricPrompt(
        activity: AppCompatActivity,
        success: (biometricStatus: BiometricStatus) -> Unit
    ) {
        val executor = ContextCompat.getMainExecutor(activity)
        val biometricPrompt = BiometricPrompt(activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        activity,
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    )
                        .show()
                    success(BiometricStatus.BIOMETRIC_FAILED)
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    success(BiometricStatus.BIOMETRIC_SUCCESS)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    success(BiometricStatus.BIOMETRIC_RETRYING)
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(appContext.getString(R.string.biometric_prompt_title))
            .setSubtitle(appContext.getString(R.string.biometric_prompt_subtitle))
            .setNegativeButtonText(appContext.getString(R.string.cancel))
            .build()

        biometricPrompt.authenticate(promptInfo)

    }
}

enum class BiometricStatus {
    BIOMETRIC_IDLE,
    BIOMETRIC_NOT_FOUND,
    BIOMETRIC_SUCCESS,
    BIOMETRIC_FAILED,
    BIOMETRIC_RETRYING
}