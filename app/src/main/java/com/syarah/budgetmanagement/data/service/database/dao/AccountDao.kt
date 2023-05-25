package com.syarah.budgetmanagement.data.service.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import com.syarah.budgetmanagement.data.dto.AccountLocalDto
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert
    suspend fun addAccount(account: AccountLocalDto)

    @Update
    suspend fun updateAccount(account: AccountLocalDto)

    @Delete
    suspend fun deleteAccount(account: AccountLocalDto)

    @Query("SELECT * FROM accounts")
    fun getAccounts(): Flow<List<AccountLocalDto>>

    @Query("SELECT * FROM accounts")
  suspend  fun getAccountsBlocking(): List<AccountLocalDto>

    @Query("SELECT * FROM accounts WHERE id = :id")
    suspend fun getAccount(id: Int): AccountLocalDto

   @Insert
   suspend fun saveAccounts(accounts: List<AccountLocalDto>)
}
