package com.syarah.budgetmanagement.data.service.database.dao

import androidx.room.*
import com.syarah.budgetmanagement.data.dto.MonthLocalDto
import kotlinx.coroutines.flow.Flow

@Dao
interface MonthDao {
    @Insert
    suspend fun addMonth(month: MonthLocalDto)

    @Update
    suspend fun updateMonth(month: MonthLocalDto)

    @Delete
    suspend fun deleteMonth(month: MonthLocalDto)

    @Query("SELECT * FROM months")
    fun getMonths(): Flow<List<MonthLocalDto>>

    @Query("SELECT * FROM months WHERE id = :id")
    suspend fun getMonth(id: Int): MonthLocalDto
}
