package com.syarah.budgetmanagement.data.repository

import com.syarah.budgetmanagement.data.datasource.localDataSource.MonthLocalDataSource
import com.syarah.budgetmanagement.domain.entity.Month
import com.syarah.budgetmanagement.domain.repository.MonthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultMonthRepository @Inject constructor(
    private val monthLocalDataSource: MonthLocalDataSource
) : MonthRepository {
    override suspend fun addMonth(month: Month) = monthLocalDataSource.addMonth(month)

    override suspend fun updateMonth(month: Month) = monthLocalDataSource.updateMonth(month)

    override suspend fun deleteMonth(month: Month) = monthLocalDataSource.deleteMonth(month)

    override fun getMonths(accountId: Int): Flow<List<Month>> =
        monthLocalDataSource.getMonths(accountId)
}