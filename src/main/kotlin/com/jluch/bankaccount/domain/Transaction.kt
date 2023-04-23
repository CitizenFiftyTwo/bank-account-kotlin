package com.jluch.bankaccount.domain

data class Transaction(val type: TransactionType, val amount: Amount)

enum class TransactionType {
    DEPOSIT,
    WITHDRAW
}