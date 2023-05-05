package domain.command

import bankaccount.domain.account.Account
import bankaccount.domain.account.Amount
import bankaccount.domain.account.Transaction
import bankaccount.domain.account.TransactionType.WITHDRAW
import bankaccount.domain.command.WithdrawCommand
import bankaccount.domain.command.WithdrawOperation
import bankaccount.infrastructure.repository.InMemoryAccountRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class WithdrawOperationTest {

    private val withdrawOperation = WithdrawOperation(InMemoryAccountRepository())

    @Test
    fun `should withdraw amount from account`() {
        val amountOfWithdrawal = Amount(BigDecimal(20).setScale(2))
        val withdrawCommand = WithdrawCommand(amountOfWithdrawal)

        val result = withdrawOperation.execute(withdrawCommand)

        Assertions.assertThat(result).isEqualTo(
            Account(
                balance = Amount(amountOfWithdrawal.value.negate()),
                transactions = listOf(Transaction(WITHDRAW, amountOfWithdrawal))
            )
        )
    }
}