package com.syarah.budgetmanagement.domain.entity


data class Month(
    val id: Int,
    val monthId: Int,
    val year: Int,
    val presentableDate: String = "",
    val dollarExpense: Int = 0,
    val dinarExpense: Int = 0,
    val dollarIncome: Int = 0,
    val dinarIncome: Int = 0,
    val accountId: Int = 0,
)
