package infrastructure.api

import bankaccount.domain.account.Account
import bankaccount.domain.account.Amount
import bankaccount.domain.account.Transaction
import bankaccount.domain.account.TransactionType.DEPOSIT
import bankaccount.domain.command.DepositCommand
import bankaccount.domain.command.DepositOperation
import bankaccount.infrastructure.api.AccountAdapter
import bankaccount.infrastructure.api.AccountView
import bankaccount.infrastructure.api.TransactionView
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal

@ExtendWith(MockKExtension::class)
class AccountAdapterTest {

    @MockK
    private lateinit var depositOperation: DepositOperation

    @InjectMockKs
    private lateinit var accountAdapter: AccountAdapter

    @Test
    fun `should make a deposit`() {
        val depositCommand = DepositCommand(Amount(BigDecimal(42)))
        every { depositOperation.execute(depositCommand) } returns (
                Account(
                    balance = Amount(BigDecimal(42)),
                    transactions = listOf(
                        Transaction(
                            type = DEPOSIT,
                            amount = Amount(BigDecimal(42))
                        )
                    )
                )
                )

        val result = accountAdapter.deposit(BigDecimal(42))

        assertThat(result).isEqualTo(
            AccountView(
                balance = BigDecimal(42),
                transactions = listOf(
                    TransactionView(
                        type = "DEPOSIT",
                        amount = BigDecimal(42)
                    )
                )
            )
        )
    }
}