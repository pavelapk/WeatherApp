package ru.pavelapk.weatherapp.data.db.common

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class Converters {

    @TypeConverter
    fun toLocalDateTime(dateString: String?): LocalDateTime? =
        dateString?.let { LocalDateTime.parse(it) }

    @TypeConverter
    fun toDateTimeString(date: LocalDateTime?): String? = date?.toString()


    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? = dateString?.let { LocalDate.parse(it) }

    @TypeConverter
    fun toDateString(date: LocalDate?): String? = date?.toString()

}
