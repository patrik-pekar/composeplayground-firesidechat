package com.example.composeplayground.utils

import java.math.BigDecimal
import java.util.Locale

object AmountFormatter {
    private const val CENT_EXCHANGE_RATE = 100
    private val THOUSAND_POUNDS = BigDecimal(1000)
    private val MILLION_POUNDS = BigDecimal(1000000)

    fun roundTo2DecimalPlaces(value: BigDecimal): String {
        return String.format(
            Locale.getDefault(),
            "%.2f",
            value
        )
    }

    fun setCurrencyForAmount(amount: String): String {
        return Typography.pound + amount
    }

    fun BigDecimal.poundsToCents(): Int {
        return this.multiply(BigDecimal(CENT_EXCHANGE_RATE)).toInt()
    }

    fun BigDecimal.minimumScale(): BigDecimal {
        return this.stripTrailingZeros()
            .takeIf { it.scale() >= 0 }
            ?: setScale(0)
    }

    fun Long.centsToPounds(): BigDecimal {
        return BigDecimal(this).divide(BigDecimal(CENT_EXCHANGE_RATE))
    }

    fun String.normalizeAmountText(): String {
        return this.replace(",", ".")
    }

    fun formatPoundsWithCurrency(pounds: BigDecimal): String {
        val result = when {
            pounds >= BigDecimal("1000000") -> (pounds / MILLION_POUNDS).toString() + "M"
            pounds >= BigDecimal("1000") -> (pounds / THOUSAND_POUNDS).toString() + "K"
            else -> pounds.toString()
        }
        return setCurrencyForAmount(result)
    }

    fun formatPoundsWithThousandSeparator(pounds: BigDecimal): String {
        return setCurrencyForAmount(
            String.format(
                Locale.getDefault(),
                "%,.0f",
                pounds
            )

        )
    }

}
