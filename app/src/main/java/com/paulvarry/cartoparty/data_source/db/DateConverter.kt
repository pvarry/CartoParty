package com.paulvarry.cartoparty.data_source.db

import androidx.room.TypeConverter
import java.util.*

public class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? = dateLong?.let { Date(it) }

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time
}
