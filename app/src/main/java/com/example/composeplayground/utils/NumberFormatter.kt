package com.example.composeplayground.utils

import java.math.BigDecimal
import java.util.*

object NumberFormatter {
    private const val NUMBERS_COUNT_TO_SEPARATE = 3

    fun convertTo2Digit(value: Int): String {
        return String.format(Locale.getDefault(), "%02d", value)
    }

    fun formatDecimalSeparator(value: BigDecimal): String {
        return value.setScale(value.scale().takeIf { it <= 2 } ?: 2)
            .toString()
            .split('.')
            .joinToString(".") { formatDecimalSeparator(it.toInt()) }
    }

    fun formatDecimalSeparator(value: Number): String {
        return value.toString()
            .reversed()
            .chunked(NUMBERS_COUNT_TO_SEPARATE)
            .joinToString(",")
            .reversed()
    }
}
