package bankaccount.infrastructure.api

import bankaccount.domain.account.Amount
import bankaccount.domain.command.DepositCommand
import bankaccount.domain.command.DepositOperation
import java.math.BigDecimal

class AccountAdapter(private val depositOperation: DepositOperation) {

    fun deposit(amount: BigDecimal): AccountView {
        return depositOperation.execute(DepositCommand(Amount(amount))).let {
            AccountView.from(it)
        }
    }
}