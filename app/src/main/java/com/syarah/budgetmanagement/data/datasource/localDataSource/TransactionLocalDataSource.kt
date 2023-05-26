package com.syarah.budgetmanagement.data.datasource.localDataSource

import com.syarah.budgetmanagement.data.mapper.toTransactionDetails
import com.syarah.budgetmanagement.data.mapper.toTransactionDto
import com.syarah.budgetmanagement.data.mapper.toTransactions
import com.syarah.budgetmanagement.data.service.database.dao.TransactionDao
import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.entity.TransactionDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionLocalDataSource @Inject constructor(private val transactionDao: TransactionDao) {
    suspend fun addTransaction(transaction: TransactionDetails) =
        transactionDao.addTransaction(transaction.toTransactionDto())

    suspend fun updateTransaction(transaction: Transaction) =
        transactionDao.updateTransaction(transaction.toTransactionDto())

    suspend fun deleteTransaction(transaction: Transaction) =
        transactionDao.deleteTransaction(transaction.toTransactionDto())

    fun getTransactions(): Flow<List<Transaction>> =
        transactionDao.getTransactions().map { dtoList -> dtoList.toTransactions() }

    suspend fun getTransactionDetails(id: Int): TransactionDetails =
        transactionDao.getTransactionDetails(id).toTransactionDetails()
}