package bankaccount.infrastructure.api

import bankaccount.domain.account.Account
import bankaccount.domain.account.Transaction
import java.math.BigDecimal

data class AccountView(val balance: BigDecimal, val transactions: List<TransactionView>) {
    companion object {
        fun from(account: Account) = AccountView(
            balance = account.balance.value,
            transactions = account.transactions.map { TransactionView.from(it) }
        )
    }
}

data class TransactionView(
    val type: String, val amount: BigDecimal
) {
    companion object {
        fun from(transaction: Transaction) = TransactionView(
            type = transaction.type.name,
            amount = transaction.amount.value
        )
    }
}