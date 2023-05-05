package bankaccount.infrastructure.repository

import bankaccount.domain.account.Account
import bankaccount.domain.account.AccountRepository
import bankaccount.domain.account.Amount
import java.math.BigDecimal.ZERO

class InMemoryAccountRepository : AccountRepository {

    var account = Account(Amount.of(ZERO), emptyList())
    override fun findTheAccount(): Account {
        return account
    }

    override fun save(account: Account): Account {
        this.account = account
        return account
    }
}