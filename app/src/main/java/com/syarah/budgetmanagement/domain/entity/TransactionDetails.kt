package com.syarah.budgetmanagement.domain.entity

import java.util.Date

data class TransactionDetails(
    val id: Int,
    val date: Date,
    val name: String,
    val total: Int,
    val currency: TransactionCurrency,
    val type: TransactionType,
)


enum class TransactionCurrency {
    Dinar,
    Dollar,
}

enum class TransactionType {
    Income,
    Expense,
}