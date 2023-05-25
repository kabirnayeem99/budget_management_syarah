package com.syarah.budgetmanagement.domain.repository

import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.entity.TransactionDetails
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun addTransaction(transaction: TransactionDetails)
    suspend fun updateTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transaction: Transaction)
    suspend fun getTransactions(): Flow<List<Transaction>>
    suspend fun getTransactionDetails(id: Int): TransactionDetails
}