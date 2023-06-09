package bankaccount.infrastructure.api

import bankaccount.domain.account.Amount
import bankaccount.domain.command.DepositCommand
import bankaccount.domain.command.DepositOperation
import bankaccount.domain.command.WithdrawCommand
import bankaccount.domain.command.WithdrawOperation
import java.math.BigDecimal

class AccountAdapter(
    private val depositOperation: DepositOperation,
    private val withdrawOperation: WithdrawOperation
) {

    fun deposit(amount: BigDecimal): AccountView {
        return depositOperation.execute(DepositCommand(Amount(amount))).let {
            AccountView.from(it)
        }
    }

    fun withdraw(amount: BigDecimal): AccountView {
        return withdrawOperation.execute(WithdrawCommand(Amount(amount))).let {
            AccountView.from(it)
        }
    }
}