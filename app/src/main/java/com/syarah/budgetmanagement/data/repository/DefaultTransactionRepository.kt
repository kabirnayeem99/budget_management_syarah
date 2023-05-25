package com.syarah.budgetmanagement.data.repository

import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.entity.TransactionDetails
import com.syarah.budgetmanagement.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class DefaultTransactionRepository: TransactionRepository {
    override suspend fun addTransaction(transaction: TransactionDetails) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override suspend fun getTransactions(): Flow<List<Transaction>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTransactionDetails(id: Int): TransactionDetails {
        TODO("Not yet implemented")
    }
}