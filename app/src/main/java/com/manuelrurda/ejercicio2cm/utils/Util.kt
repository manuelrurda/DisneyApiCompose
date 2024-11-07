package com.manuelrurda.ejercicio2cm.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun transformDateFormat(inputDate: String): String {
    if (inputDate.isEmpty()) return ""
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    val outputFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale("es","MX"))
    val date = inputFormat.parse(inputDate) ?: Date()
    return outputFormat.format(date)
}