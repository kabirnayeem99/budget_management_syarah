package com.syarah.budgetmanagement.domain.entity

import java.util.Date

data class Transaction(
    val id: Int,
    val date: Date,
    val name: String,
    val monthId: Int,
    val accountId: Int,
    val total: Int,
    val currency: TransactionCurrency,
    val type: TransactionType
)