package com.syarah.budgetmanagement.data.datasource.localDataSource

import com.syarah.budgetmanagement.data.mapper.toAccountDto
import com.syarah.budgetmanagement.data.mapper.toAccounts
import com.syarah.budgetmanagement.data.service.database.dao.AccountDao
import com.syarah.budgetmanagement.domain.entity.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountLocalDataSource @Inject constructor(private val accountDao: AccountDao) {
    suspend fun addAccount(account: Account) {
        accountDao.addAccount(account.toAccountDto())
    }

    suspend fun deleteAccount(account: Account) {
        accountDao.deleteAccount(account.toAccountDto())
    }

    suspend fun editAccount(account: Account) {
        accountDao.updateAccount(account.toAccountDto())
    }

    fun getAccounts(): Flow<List<Account>> {
        return accountDao.getAccounts().map { dtoList -> dtoList.toAccounts() }
    }
}