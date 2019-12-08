package com.github.redouane64.debt.internals

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class DateHelper {
    companion object {

        val Today: String
            @SuppressLint("SimpleDateFormat")
            get() = SimpleDateFormat("YYYY.M.d").format(Date())

        fun AsDateString(year: Int, month: Int, day: Int) : String {
            return GregorianCalendar(year, month, day, 0, 0, 0).let {
                SimpleDateFormat("YYYY.M.d").format(it.time)
            }
        }

    }
}