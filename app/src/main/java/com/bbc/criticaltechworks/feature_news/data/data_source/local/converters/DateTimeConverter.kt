package com.bbc.criticaltechworks.feature_news.data.data_source.local.converters

import androidx.room.TypeConverter
import java.util.*

class DateTimeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}