package bankaccount.domain.command

import bankaccount.domain.account.Account
import bankaccount.domain.account.AccountRepository
import bankaccount.domain.account.Amount

class WithdrawOperation(private val accountRepository: AccountRepository) {
    fun execute(withdrawCommand: WithdrawCommand): Account {
        return accountRepository.findTheAccount().let {
            val accountAfterWithdraw = it.withdraw(withdrawCommand.amount)
            accountRepository.save(accountAfterWithdraw)
        }
    }
}

data class WithdrawCommand(
    val amount: Amount
)