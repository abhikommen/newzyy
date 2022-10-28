package com.bbc.criticaltechworks.common.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson

/**
 * Currently Compose navigation only supports selected argument types while navigation from one
 * screen to another. CustomNavType is a custom NavType that support any type.
 * @param type : Class<T> --> Class type of the object, eg String::class.java
 */
@Suppress("DEPRECATION")
class CustomNavType<T : Parcelable>(private val type: Class<T>) :
    NavType<T>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, type)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): T {
        return Gson().fromJson(value, type)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }
}