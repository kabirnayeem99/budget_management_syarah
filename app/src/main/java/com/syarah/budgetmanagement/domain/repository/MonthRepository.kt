package com.syarah.budgetmanagement.domain.repository

import com.syarah.budgetmanagement.domain.entity.Month
import kotlinx.coroutines.flow.Flow

interface MonthRepository {
    suspend fun addMonth(month: Month)
    suspend fun updateMonth(month: Month)
    suspend fun deleteMonth(month: Month)
    suspend fun getMonths(): Flow<List<Month>>
    suspend fun getMonth(id: Int): Month
}