package com.syarah.budgetmanagement.domain.entity

import java.util.Date

data class TransactionDetails(
    val id: Int = -1,
    val date: Date = Date(),
    val name: String  = "",
    val amount: Int = 0,
    val currency: TransactionCurrency = TransactionCurrency.Dinar,
    val type: TransactionType = TransactionType.Income,
    val monthId: Int = -1,
    val accountId: Int = -1,
    val year: Int = -1,
)


enum class TransactionCurrency {
    Dinar, Dollar,
}

enum class TransactionType {
    Income, Expense,
}