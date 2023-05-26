package com.syarah.budgetmanagement.data.service.database.dao

import androidx.room.Dao
import androidx.room.*
import com.syarah.budgetmanagement.data.dto.TransactionLocalDto
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun addTransaction(transaction: TransactionLocalDto)

    @Update
    suspend fun updateTransaction(transaction: TransactionLocalDto)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionLocalDto)

    @Query("SELECT * FROM transactions where accountId=:accountId AND monthId = :monthId")
    fun getTransactions(accountId: Int, monthId: Int): Flow<List<TransactionLocalDto>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionDetails(id: Int): TransactionLocalDto
}