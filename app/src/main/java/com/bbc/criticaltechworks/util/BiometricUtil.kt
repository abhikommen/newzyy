package com.bbc.criticaltechworks.util

import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.*
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.bbc.criticaltechworks.R

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
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                    )
                }
                context.startActivityForResult(enrollIntent, 1221)
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
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
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