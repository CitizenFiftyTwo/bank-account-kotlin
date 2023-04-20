package com.jluch.domain

import java.math.BigDecimal
import java.math.RoundingMode

private const val DEFAULT_SCALE = 2

data class Amount(val value: BigDecimal) {

    fun add(amount: Amount): Amount {
        return this.copy(value = value.add(amount.value))
    }

    fun subtract(amount: Amount): Amount {
        return this.copy(value = value.subtract(amount.value))
    }

    companion object {
        fun of(value: BigDecimal): Amount {
            return Amount(value.setScale(DEFAULT_SCALE, RoundingMode.HALF_UP))
        }
    }
}