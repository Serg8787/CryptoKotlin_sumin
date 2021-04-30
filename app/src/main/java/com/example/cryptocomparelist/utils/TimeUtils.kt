package com.example.cryptocomparelist.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    fun convertTimestamtToTime(timestap:Long?) :String{
        if(timestap==null) return ""

        val stamp = Timestamp(timestap * 1000)

        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern,Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }
}