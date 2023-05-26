package com.syarah.budgetmanagement.data.datasource.localDataSource

import com.google.gson.Gson
import com.syarah.budgetmanagement.data.mapper.toAccountDto
import com.syarah.budgetmanagement.data.mapper.toAccounts
import com.syarah.budgetmanagement.data.service.database.dao.AccountDao
import com.syarah.budgetmanagement.data.service.database.dao.TransactionDao
import com.syarah.budgetmanagement.domain.entity.Account
import com.syarah.budgetmanagement.domain.entity.TransactionCurrency
import com.syarah.budgetmanagement.domain.entity.TransactionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountLocalDataSource @Inject constructor(
    private val accountDao: AccountDao,
    private val transactionDao: TransactionDao,
) {
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
        return accountDao.getAccounts().map { dtoList ->
            withContext(Dispatchers.Default) {
                dtoList.toAccounts().map { account -> calculateAccount(account) }
            }
        }
    }

    private val gson by lazy { Gson() }
    suspend fun getSerialisedAccounts(): String {
        return withContext(Dispatchers.Default) {
            val accounts = accountDao.getAccountsBlocking()
                .toAccounts()
                .map { calculateAccount(it) }
            gson.toJson(accounts)
        }
    }

    private suspend fun calculateAccount(account: Account): Account {
        val transactions = transactionDao.getTransactionsByAccount(account.id)
        var dinarExpense = 0
        var dinarIncome = 0
        var dollarExpense = 0
        var dollarIncome = 0
        transactions.forEach { transaction ->
            when (transaction.currency) {
                TransactionCurrency.Dinar -> {
                    when (transaction.type) {
                        TransactionType.Expense -> dinarExpense += transaction.total
                        TransactionType.Income -> dinarIncome += transaction.total
                    }
                }

                TransactionCurrency.Dollar -> {
                    when (transaction.type) {
                        TransactionType.Expense -> dollarExpense += transaction.total
                        TransactionType.Income -> dollarIncome += transaction.total
                    }
                }
            }
        }
        val dollarAmount = dollarIncome - dollarExpense
        val dinarAmount = dinarIncome - dinarExpense
        return account.copy(dollarAmount = dollarAmount, dinarAmount = dinarAmount)
    }
}