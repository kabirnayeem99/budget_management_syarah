package com.syarah.budgetmanagement.presentation.transactionDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.usecase.transaction.DeleteTransactionUseCase
import com.syarah.budgetmanagement.domain.usecase.transaction.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionDetailsViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTransactionUseCase(transaction)
        }
    }

    fun fetchTransactions(accountId: Int, monthId: Int, yearId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getTransactionsUseCase(accountId, monthId, yearId).collect { result ->
                result.onSuccess { transactions -> _uiState.update { it.copy(transactions = transactions) } }
            }
        }
    }

}