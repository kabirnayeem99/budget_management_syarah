package com.syarah.budgetmanagement.presentation.transactionUpsert

import com.syarah.budgetmanagement.domain.entity.TransactionDetails

data class TransactionUpsertUiState(
    val isLoading: Boolean = false,
    val transactionDetails: TransactionDetails? = null,
    val accountId: Int = -1,
    val monthId: Int = -1,
    val year: Int = -1,
)