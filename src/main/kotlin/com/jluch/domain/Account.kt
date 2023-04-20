package com.jluch.domain

data class Account(val balance: Amount) {

    fun deposit(amount: Amount): Account {
        return this.copy(balance = balance.add(amount))
    }

    fun withdraw(amount: Amount): Account {
        return this.copy(balance = balance.subtract(amount))
    }
}