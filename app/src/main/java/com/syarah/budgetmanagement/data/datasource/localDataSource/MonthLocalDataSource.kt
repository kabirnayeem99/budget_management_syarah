package com.syarah.budgetmanagement.data.datasource.localDataSource

import com.syarah.budgetmanagement.data.mapper.toMonthDto
import com.syarah.budgetmanagement.data.mapper.toMonths
import com.syarah.budgetmanagement.data.service.database.dao.MonthDao
import com.syarah.budgetmanagement.domain.entity.Month
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MonthLocalDataSource @Inject constructor(private val monthDao: MonthDao) {

    suspend fun addMonth(month: Month) = monthDao.addMonth(month.toMonthDto())

    suspend fun updateMonth(month: Month) = monthDao.updateMonth(month.toMonthDto())

    suspend fun deleteMonth(month: Month) = monthDao.deleteMonth(month.toMonthDto())

     fun getMonths(): Flow<List<Month>> =
        monthDao.getMonths().map { dtoList -> dtoList.toMonths() }

}