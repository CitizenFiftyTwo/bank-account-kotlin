package com.jluch.domain

import java.math.BigDecimal

data class Amount(val value: BigDecimal) {
    fun add(amount: Amount): Amount {
        return this.copy(value = value + amount.value)
    }
}