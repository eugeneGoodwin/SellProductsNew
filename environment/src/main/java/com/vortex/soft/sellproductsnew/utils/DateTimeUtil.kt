package com.vortex.soft.sellproductsnew.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {

    const val SELLPRODUCTS_DATE_FORMAT = "dd.MM.yyyy"
    const val SELLPRODUCTS_TIME_FORMAT = "HH:mm"
    const val SELLPRODUCTS_FULL_FORMAT = "dd.MM.yyyy HH:mm"

    val MILISECONDS: Long = 1000L
    val SECONDS = MILISECONDS * 60L
    val HOURS = SECONDS * 60L
    val DAYS = HOURS * 24L

    fun toSeconds(d: Long) = (d / MILISECONDS).toInt()
    fun toMinutes(d: Long) = (d / SECONDS).toInt()
    fun toHours(d: Long) = (d / HOURS).toInt()
    fun toDays(d: Long): Int = (d / DAYS).toInt()

    fun getCurrentDateString(): String {
        val today = Calendar.getInstance().getTime()
        return SimpleDateFormat(SELLPRODUCTS_DATE_FORMAT).format(today)
    }
}