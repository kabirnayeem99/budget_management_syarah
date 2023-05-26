package com.syarah.budgetmanagement.presentation.transactionUpsert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syarah.budgetmanagement.domain.entity.TransactionCurrency
import com.syarah.budgetmanagement.domain.entity.TransactionType
import com.syarah.budgetmanagement.domain.usecase.transaction.GetTransactionDetailsUseCase
import com.syarah.budgetmanagement.domain.usecase.transaction.CreateNewTransactionUseCase
import com.syarah.budgetmanagement.domain.usecase.transaction.UpdateTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionUpsertViewModel @Inject constructor(
    private val getTransactionDetailsUseCase: GetTransactionDetailsUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase,
    private val createNewTransactionUseCase: CreateNewTransactionUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionUpsertUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchTransactionDetails(transactionId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getTransactionDetailsUseCase(transactionId).onSuccess { transaction ->
                _uiState.update { it.copy(transactionDetails = transaction) }
            }
        }
    }

    fun createOrUpdateTransaction(
        transactionName: String,
        amount: Int,
        type: TransactionType,
        currency: TransactionCurrency,
        monthId: Int,
        accountId: Int,
        onSaved: () -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val isUpdate = uiState.value.transactionDetails != null
            if (!isUpdate) createNewTransaction(transactionName, amount, type, currency,monthId, accountId)
            else updateTransaction(transactionName, amount, type, currency,monthId, accountId)
            withContext(Dispatchers.Main) {
                onSaved()
            }
        }
    }

    private fun updateTransaction(
        transactionName: String,
        amount: Int,
        type: TransactionType,
        currency: TransactionCurrency,
        monthId: Int,
        accountId: Int,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val editingTransaction = uiState.value.transactionDetails!!;
            updateTransactionUseCase(
                editingTransaction.copy(
                    name = transactionName,
                    accountId = accountId,
                    monthId = monthId,
                    year = uiState.value.year,
                    currency = currency,
                    type = type,
                    amount = amount,
                )
            )
        }
    }

    private fun createNewTransaction(
        transactionName: String,
        amount: Int,
        type: TransactionType,
        currency: TransactionCurrency,
        monthId: Int,
        accountId: Int,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            createNewTransactionUseCase(
                transactionName = transactionName,
                amount = amount,
                type = type,
                currency = currency,
                year = uiState.value.year,
                monthId = monthId,
                accountId = accountId,
            )
        }
    }
}