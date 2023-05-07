package bankaccount.infrastructure.api

import bankaccount.domain.account.Account
import bankaccount.domain.account.Amount
import bankaccount.domain.account.Transaction
import bankaccount.domain.account.TransactionType.DEPOSIT
import bankaccount.domain.account.TransactionType.WITHDRAW
import bankaccount.domain.command.DepositCommand
import bankaccount.domain.command.DepositOperation
import bankaccount.domain.command.WithdrawCommand
import bankaccount.domain.command.WithdrawOperation
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

    @MockK
    private lateinit var withdrawOperation: WithdrawOperation


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

    @Test
    fun `should make a withdraw`() {
        val wtihdrawCommand = WithdrawCommand(Amount(BigDecimal(42)))
        every { withdrawOperation.execute(wtihdrawCommand) } returns (
                Account(
                    balance = Amount(BigDecimal(42)),
                    transactions = listOf(
                        Transaction(
                            type = WITHDRAW,
                            amount = Amount(BigDecimal(42))
                        )
                    )
                )
                )

        val result = accountAdapter.withdraw(BigDecimal(42))

        assertThat(result).isEqualTo(
            AccountView(
                balance = BigDecimal(42),
                transactions = listOf(
                    TransactionView(
                        type = "WITHDRAW",
                        amount = BigDecimal(42)
                    )
                )
            )
        )
    }
}