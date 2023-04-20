package com.jluch.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigDecimal

class AccountTest {

    @DisplayName("Deposit should add money to balance")
    @ParameterizedTest(name = "Deposit {1} in account with a balance of {0} should result with {2} in balance")
    @CsvSource(
            "0, 0, 0.00",
            "0, 42, 42.00",
            "0, 42.5, 42.50",
            "42, 8, 50.00",
            "42.5, 7.5, 50.00",
            "42.55, 7.45, 50.00",
            "42.554, 7.454, 50.00",
            "42.555, 7.455, 50.02",
    )
    fun `deposit money is OK`(initialBalanceValue: BigDecimal,
                              depositAmountValue: BigDecimal,
                              expectedBalanceValue: BigDecimal) {
        val account = accountWithInitialAmount(initialBalanceValue)

        val accountAfterDeposit = account.deposit(Amount.of(depositAmountValue))

        assertThat(accountAfterDeposit.balance).isEqualTo(Amount(expectedBalanceValue))
    }

    @DisplayName("Withdraw should remove money from balance")
    @ParameterizedTest(name = "Withdraw {1} in account with a balance of {0} should result with {2} in balance")
    @CsvSource(
            "0, 1, -1.00",
            "42, 41, 1.00",
            "42.5, 41, 1.50",
            "42.5, 41.5, 1.00",
            "42.55, 41.55, 1.00",
            "42.55, 41.554, 1.00",
            "42.55, 41.555, 0.99",
    )
    fun `withdraw money is OK`(initialBalanceValue: BigDecimal,
                               depositAmountValue: BigDecimal,
                               expectedBalanceValue: BigDecimal) {
        val account = accountWithInitialAmount(initialBalanceValue)

        val accountAfterWithdraw = account.withdraw(Amount.of(depositAmountValue))

        assertThat(accountAfterWithdraw.balance).isEqualTo(Amount(expectedBalanceValue))
    }

    private fun accountWithInitialAmount(amount: BigDecimal): Account {
        return Account(Amount.of(amount))
    }
}