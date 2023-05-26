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

    init {
        fetchMonths()
    }


    fun addOrUpdateMonth(month: Int, year: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val isUpdate = uiState.value.editableMonth != null
            if (isUpdate) updateMonth(month, year) else addMonth(month, year)
        }
    }

    private fun addMonth(month: Int, year: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewMonthUseCase(month, year)
        }
    }

    private fun updateMonth(month: Int, year: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val updatingMonth = uiState.value.editableMonth!!
            updateMonthInfoUseCase(updatingMonth.copy(monthId = month, year = year))
        }
    }


    fun deleteMonth(month: Month) {
        viewModelScope.launch(Dispatchers.IO) {
            removeMonthUseCase(month)
        }
    }

    private fun fetchMonths() {
        viewModelScope.launch(Dispatchers.IO) {
            getMonthsUseCase().collect { results ->
                results.onSuccess {
                    _uiState.update { it.copy(months = results.getOrNull() ?: emptyList()) }
                }
            }
        }
    }

}