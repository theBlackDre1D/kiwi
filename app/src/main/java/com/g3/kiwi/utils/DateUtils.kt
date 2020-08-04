package com.g3.kiwi.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val DATE_FORMAT = "dd/MM/yyyy"

    fun getTodayDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return dateFormatter.format(Date(calendar.timeInMillis))
    }

    fun getDateDaysFromNow(days: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, days)

        val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return dateFormatter.format(Date(calendar.timeInMillis))
    }
}