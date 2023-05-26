package com.syarah.budgetmanagement.presentation.months

import com.syarah.budgetmanagement.domain.entity.Month

data class MonthsUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val months: List<Month> = emptyList(),
    val editableMonth: Month? = null,
)