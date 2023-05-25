package com.syarah.budgetmanagement.data.repository

import com.syarah.budgetmanagement.domain.entity.Month
import com.syarah.budgetmanagement.domain.repository.MonthRepository
import kotlinx.coroutines.flow.Flow

class DefaultMonthRepository: MonthRepository {
    override suspend fun addMonth(month: Month) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMonth(month: Month) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMonth(month: Month) {
        TODO("Not yet implemented")
    }

    override suspend fun getMonths(): Flow<List<Month>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMonth(id: Int): Month {
        TODO("Not yet implemented")
    }
}