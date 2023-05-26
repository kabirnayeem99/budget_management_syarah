package com.syarah.budgetmanagement.domain.repository

import com.syarah.budgetmanagement.domain.entity.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun addAccount(account: Account)
    suspend fun updateAccount(account: Account)
    suspend fun deleteAccount(account: Account)
    fun getAccounts(): Flow<List<Account>>
    suspend fun getSerialisedAccounts(): String
    suspend fun saveSerialisedAccounts(serialisedAccounts: String)
}