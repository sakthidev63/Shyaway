package com.sakthi.shyaway.core

import androidx.compose.ui.graphics.Color
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object Util {

    fun String.parseRGBAColor(): Color {
        val regex = """rgba\((\d+),\s*(\d+),\s*(\d+),\s*([\d.]+)\)""".toRegex()
        val matchResult = regex.find(this)

        return if (matchResult != null) {
            val (r, g, b, a) = matchResult.destructured
            Color(
                red = r.toInt() / 255f,
                green = g.toInt() / 255f,
                blue = b.toInt() / 255f,
                alpha = a.toFloat()
            )
        } else {
            Color.Black
        }
    }


}