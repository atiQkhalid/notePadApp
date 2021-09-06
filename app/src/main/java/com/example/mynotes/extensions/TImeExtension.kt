package com.example.mynotes.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun dateTime(): String {
    val dft = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")
    val currentTime = LocalDateTime.now()
    val dateTime = dft.format(currentTime)
    return dateTime.toString()

}