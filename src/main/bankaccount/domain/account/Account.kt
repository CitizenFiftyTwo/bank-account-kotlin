package bankaccount.domain.account

import bankaccount.domain.account.TransactionType.DEPOSIT
import bankaccount.domain.account.TransactionType.WITHDRAW

data class Account(val balance: Amount, val transactions: List<Transaction>) {

    fun deposit(amount: Amount): Account {
        return this.copy(
            balance = balance.add(amount),
            transactions = transactions.plus(Transaction(DEPOSIT, amount))
        )
    }

    fun withdraw(amount: Amount): Account {
        return this.copy(
            balance = balance.subtract(amount),
            transactions = transactions.plus(Transaction(WITHDRAW, amount))
        )
    }
}