package bankaccount.domain.account

interface AccountRepository {
    fun findTheAccount(): Account

    fun save(account: Account): Account
}