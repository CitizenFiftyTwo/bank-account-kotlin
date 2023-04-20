package com.jluch.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal

class AmountTest {

    @ParameterizedTest(name = "Amount of {0} should be instantiated with value {1}")
    @CsvSource(
            "0, 0.00",
            "42, 42.00",
            "42.5, 42.50",
            "42.52, 42.52",
            "42.524, 42.52",
            "42.525, 42.53",
    )
    @DisplayName(value = "amountOf should instantiate amount with scale of 2")
    fun `amountOf is ok`(inputValue: BigDecimal, expectedValue: BigDecimal) {
        val amount = Amount.of(inputValue)

        Assertions.assertThat(amount.value).isEqualTo(expectedValue)
    }
}