package com.syarah.budgetmanagement.presentation.transactionDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.usecase.transaction.DeleteTransactionUseCase
import com.syarah.budgetmanagement.domain.usecase.transaction.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionDetailsViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
) : ViewModel() {


    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTransactionUseCase(transaction)
        }
    }

}