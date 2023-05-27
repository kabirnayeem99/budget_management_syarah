package com.syarah.budgetmanagement.presentation.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syarah.budgetmanagement.domain.entity.Account
import com.syarah.budgetmanagement.domain.usecase.account.CreateNewAccountUseCase
import com.syarah.budgetmanagement.domain.usecase.account.DeleteAccountUseCase
import com.syarah.budgetmanagement.domain.usecase.account.GetAccountsUseCase
import com.syarah.budgetmanagement.domain.usecase.account.GetSerialisedAccounts
import com.syarah.budgetmanagement.domain.usecase.account.UpdateAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val createNewAccountUseCase: CreateNewAccountUseCase,
    private val getAccountsUseCase: GetAccountsUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase,
    private val getSerialisedAccounts: GetSerialisedAccounts,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AccountsUiState())
    val uiState = _uiState.asStateFlow()


    fun createOrUpdateAccount(name: String, onError: (String) -> Unit, onFinished: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            if (name.isEmpty()) {
                withContext(Dispatchers.Main) {
                    onError("Account name can't be empty")
                }
                return@launch
            }
            val isUpdate = uiState.value.editableAccount != null
            if (isUpdate) updateAccount(name) else createNewAccount(name)
            withContext(Dispatchers.Main) {
                onFinished()
            }
        }
    }

    private fun createNewAccount(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            createNewAccountUseCase.invoke(name)
        }
    }

    private fun updateAccount(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val editableAccount = uiState.value.editableAccount!!
            updateAccountUseCase(editableAccount.copy(name = name))
        }
    }

    fun deleteAccount(account: Account) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAccountUseCase(account)
        }
    }

    fun setEditableAccount(account: Account?) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(editableAccount = account) }
        }
    }

    fun fetchAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            getAccountsUseCase().collect { results ->
                results.onFailure { e ->
                    _uiState.update { it.copy(errorMessage = e.localizedMessage) }
                }.onSuccess { accounts ->
                    Timber.d(accounts.toString())
                    _uiState.update { it.copy(accounts = accounts) }
                }
            }
        }
    }

    fun fetchSerialisedAccounts(onFetched: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            getSerialisedAccounts().onSuccess { serialisedAccounts ->
                withContext(Dispatchers.Main) {
                    onFetched(serialisedAccounts)
                }
            }
        }
    }
}