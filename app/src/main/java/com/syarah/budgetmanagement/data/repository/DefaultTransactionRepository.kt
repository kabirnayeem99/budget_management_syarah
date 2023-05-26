package com.syarah.budgetmanagement.data.repository

import com.syarah.budgetmanagement.data.datasource.localDataSource.TransactionLocalDataSource
import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.entity.TransactionDetails
import com.syarah.budgetmanagement.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultTransactionRepository @Inject constructor(
    private val transactionLocalDataSource: TransactionLocalDataSource,
) : TransactionRepository {
    override suspend fun addTransaction(transaction: TransactionDetails) =
        transactionLocalDataSource.addTransaction(transaction)

    override suspend fun updateTransaction(transaction: TransactionDetails) =
        transactionLocalDataSource.updateTransaction(transaction)

    override suspend fun deleteTransaction(transaction: Transaction) =
        transactionLocalDataSource.deleteTransaction(transaction)


    override fun getTransactions(
        accountId: Int,
        monthId: Int,
        yearId: Int
    ): Flow<List<Transaction>> =
        transactionLocalDataSource.getTransactions()

    override suspend fun getTransactionDetails(id: Int): TransactionDetails =
        transactionLocalDataSource.getTransactionDetails(id)
}