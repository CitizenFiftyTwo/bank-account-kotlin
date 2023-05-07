package bankaccount.domain.command

import bankaccount.domain.account.Account
import bankaccount.domain.account.Amount
import bankaccount.domain.account.Transaction
import bankaccount.domain.account.TransactionType.DEPOSIT
import bankaccount.infrastructure.repository.InMemoryAccountRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class DepositOperationTest {

    private val depositOperation = DepositOperation(InMemoryAccountRepository())

    @Test
    fun `should deposit amount on account`() {
        val amountOfDeposit = Amount(BigDecimal(20).setScale(2))
        val depositCommand = DepositCommand(amountOfDeposit)

        val result = depositOperation.execute(depositCommand)

        assertThat(result).isEqualTo(
            Account(
                balance = amountOfDeposit,
                transactions = listOf(Transaction(DEPOSIT, amountOfDeposit))
            )
        )
    }
}