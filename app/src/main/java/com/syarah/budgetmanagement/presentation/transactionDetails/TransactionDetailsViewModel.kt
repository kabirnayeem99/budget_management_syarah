package com.syarah.budgetmanagement.presentation.transactionDetails

import androidx.lifecycle.ViewModel
import com.syarah.budgetmanagement.domain.usecase.transaction.DeleteTransactionUseCase
import com.syarah.budgetmanagement.domain.usecase.transaction.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionDetailsViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
) : ViewModel() {



}