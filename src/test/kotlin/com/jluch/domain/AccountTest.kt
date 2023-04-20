package com.jluch.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class AccountTest {

    @Test
    fun `deposit should add money to balance`() {
        val account = Account(Amount(BigDecimal.ZERO))

        val accountAfterDeposit = account.deposit(Amount(BigDecimal(42)))

        Assertions.assertThat(accountAfterDeposit.balance).isEqualTo(Amount(BigDecimal(42)))
    }
}