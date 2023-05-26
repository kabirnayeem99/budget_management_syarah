package com.syarah.budgetmanagement.presentation.transactionUpsert

import androidx.lifecycle.ViewModel
import com.syarah.budgetmanagement.domain.usecase.transaction.GetTransactionDetailsUseCase
import com.syarah.budgetmanagement.domain.usecase.transaction.UpdateTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionUpsertViewModel @Inject constructor(
    private val getTransactionDetailsUseCase: GetTransactionDetailsUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase,
) : ViewModel() {

}