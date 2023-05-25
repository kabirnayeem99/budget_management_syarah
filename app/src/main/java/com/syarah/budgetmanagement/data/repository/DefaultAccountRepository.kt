package com.syarah.budgetmanagement.data.repository

import com.syarah.budgetmanagement.domain.entity.Account
import com.syarah.budgetmanagement.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

class DefaultAccountRepository: AccountRepository {
    override suspend fun addAccount(account: Account) {
        TODO("Not yet implemented")
    }

    override suspend fun updateAccount(account: Account) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAccount(account: Account) {
        TODO("Not yet implemented")
    }

    override suspend fun getAccounts(): Flow<List<Account>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAccount(id: Int): Account {
        TODO("Not yet implemented")
    }

    override suspend fun getSerialisedAccounts(): String {
        TODO("Not yet implemented")
    }

    override suspend fun saveSerialisedAccounts(serialisedAccounts: String) {
        TODO("Not yet implemented")
    }
}