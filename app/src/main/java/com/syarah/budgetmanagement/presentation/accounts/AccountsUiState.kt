package com.syarah.budgetmanagement.presentation.accounts

import com.syarah.budgetmanagement.domain.entity.Account

data class AccountsUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val accounts: List<Account> = emptyList(),
    val editableAccount: Account? = null,
)
