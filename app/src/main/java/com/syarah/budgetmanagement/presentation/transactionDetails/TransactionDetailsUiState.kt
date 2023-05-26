package com.syarah.budgetmanagement.presentation.transactionDetails

import com.syarah.budgetmanagement.domain.entity.Transaction

data class TransactionDetailsUiState(
    val isLoading: Boolean = false,
    val transactions: List<Transaction> = emptyList(),
)