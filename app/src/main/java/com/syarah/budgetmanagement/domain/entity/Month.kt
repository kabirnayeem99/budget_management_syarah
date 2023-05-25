package com.syarah.budgetmanagement.domain.entity


data class Month(
    val id: Int,
    val monthId: Int,
    val year: Int,
    val presentableDate: String,
    val dollarExpense: Int,
    val dinarExpense: Int,
    val dollarIncome: Int,
    val dinarIncome: Int,
)
