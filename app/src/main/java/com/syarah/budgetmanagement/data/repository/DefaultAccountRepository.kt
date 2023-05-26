package com.syarah.budgetmanagement.data.repository

import com.syarah.budgetmanagement.data.datasource.localDataSource.AccountLocalDataSource
import com.syarah.budgetmanagement.domain.entity.Account
import com.syarah.budgetmanagement.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultAccountRepository @Inject constructor(
    private val accountLocalDataSource: AccountLocalDataSource,
) : AccountRepository {
    override suspend fun addAccount(account: Account) = accountLocalDataSource.addAccount(account)


    override suspend fun updateAccount(account: Account) =
        accountLocalDataSource.editAccount(account)

    override suspend fun deleteAccount(account: Account) =
        accountLocalDataSource.deleteAccount(account)

    override fun getAccounts(): Flow<List<Account>> = accountLocalDataSource.getAccounts()

    override suspend fun getAccount(id: Int): Account {
        TODO("Not yet implemented")
    }

    override suspend fun getSerialisedAccounts(): String =
        accountLocalDataSource.getSerialisedAccounts()

    override suspend fun saveSerialisedAccounts(serialisedAccounts: String) {
        TODO("Not yet implemented")
    }
}