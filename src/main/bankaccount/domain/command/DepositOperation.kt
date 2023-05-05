package bankaccount.domain.command

import bankaccount.domain.account.Account
import bankaccount.domain.account.AccountRepository
import bankaccount.domain.account.Amount

class DepositOperation(private val accountRepository: AccountRepository) {
    fun execute(depositCommand: DepositCommand): Account {
        return accountRepository.findTheAccount().let {
            val accountAfterDeposit = it.deposit(depositCommand.amount)
            accountRepository.save(accountAfterDeposit)
        }
    }
}

data class DepositCommand(
    val amount: Amount
)