package com.klyukvin.domain.usecase

import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class GetCurrentDateUseCase {

    private val calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"))
    private val locale = Locale("ru")

    operator fun invoke(): String {
        val day = getDay()
        val month = getMonth()
        val year = getYear()
        val hour = getHour()
        val minute = getMinute()

        return "$day $month $year в $hour:$minute МСК"
    }

    private fun getDay(): String {
        return calendar.get(Calendar.DAY_OF_MONTH).toString()
    }

    private fun getMonth(): String {
        return calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, locale).toString()
    }

    private fun getYear(): String {
        return calendar.get(Calendar.YEAR).toString()
    }

    private fun getHour(): String {
        return calendar.get(Calendar.HOUR_OF_DAY).toString()
    }

    private fun getMinute(): String {
        val minuteNumber = calendar.get(Calendar.MINUTE)
        return String.format(locale, "%02d", minuteNumber)
    }
}