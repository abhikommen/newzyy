package com.bbc.criticaltechworks.common.utils

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson


/**
 * Custom Parcelable that serialize object to @String
 */
abstract class JsonParcelable : Parcelable {
    fun toJson(): String {
        return Uri.encode(Gson().toJson(this))
    }
}