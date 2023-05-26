package com.syarah.budgetmanagement.data.repository

import com.syarah.budgetmanagement.data.mapper.toTransactionDetails
import com.syarah.budgetmanagement.data.mapper.toTransactionDto
import com.syarah.budgetmanagement.data.mapper.toTransactions
import com.syarah.budgetmanagement.data.service.database.dao.TransactionDao
import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.entity.TransactionDetails
import com.syarah.budgetmanagement.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultTransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao,
) : TransactionRepository {
    override suspend fun addTransaction(transaction: TransactionDetails) =
        transactionDao.addTransaction(transaction.toTransactionDto())

    override suspend fun updateTransaction(transaction: Transaction) =
        transactionDao.updateTransaction(transaction.toTransactionDto())

    override suspend fun deleteTransaction(transaction: Transaction) =
        transactionDao.deleteTransaction(transaction.toTransactionDto())

    override fun getTransactions(): Flow<List<Transaction>> =
        transactionDao.getTransactions().map { dtoList -> dtoList.toTransactions() }

    override suspend fun getTransactionDetails(id: Int): TransactionDetails =
        transactionDao.getTransactionDetails(id).toTransactionDetails()
}