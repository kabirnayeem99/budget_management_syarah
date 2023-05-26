package com.syarah.budgetmanagement.presentation.months

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syarah.budgetmanagement.domain.entity.Month
import com.syarah.budgetmanagement.domain.usecase.month.AddNewMonthUseCase
import com.syarah.budgetmanagement.domain.usecase.month.GetMonthsUseCase
import com.syarah.budgetmanagement.domain.usecase.month.RemoveMonthUseCase
import com.syarah.budgetmanagement.domain.usecase.month.UpdateMonthInfoUseCase
import com.syarah.budgetmanagement.presentation.accounts.AccountsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MonthsViewModel @Inject constructor(
    private val addNewMonthUseCase: AddNewMonthUseCase,
    private val getMonthsUseCase: GetMonthsUseCase,
    private val removeMonthUseCase: RemoveMonthUseCase,
    private val updateMonthInfoUseCase: UpdateMonthInfoUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MonthsUiState())
    val uiState = _uiState.asStateFlow()


    fun addOrUpdateMonth(month: Int, year: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("$month-$year")
            val isUpdate = uiState.value.editableMonth != null
            if (isUpdate) updateMonth(month, year) else addMonth(month, year)
        }
    }

    private fun addMonth(month: Int, year: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewMonthUseCase(month, year, uiState.value.accountId)
        }
    }

    private fun updateMonth(month: Int, year: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val updatingMonth = uiState.value.editableMonth!!
            updateMonthInfoUseCase(updatingMonth.copy(monthId = month, year = year))
        }
    }

    fun setEditingMonth(month: Month?) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(editableMonth = month) }
        }
    }


    fun deleteMonth(month: Month) {
        viewModelScope.launch(Dispatchers.IO) {
            removeMonthUseCase(month)
        }
    }

    fun fetchMonths(accountId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getMonthsUseCase(accountId).collect { results ->
                results.onSuccess { months ->
                    Timber.d(months.toString())
                    _uiState.update { it.copy(months = months, accountId = accountId) }
                }
            }
        }
    }

}