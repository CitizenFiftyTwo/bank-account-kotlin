package bankaccount.domain.account

data class Transaction(val type: TransactionType, val amount: Amount)

enum class TransactionType {
    DEPOSIT,
    WITHDRAW
}