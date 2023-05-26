package com.syarah.budgetmanagement.data.datasource.localDataSource

import com.syarah.budgetmanagement.data.mapper.toMonthDto
import com.syarah.budgetmanagement.data.mapper.toMonths
import com.syarah.budgetmanagement.data.service.database.dao.MonthDao
import com.syarah.budgetmanagement.data.service.database.dao.TransactionDao
import com.syarah.budgetmanagement.domain.entity.Month
import com.syarah.budgetmanagement.domain.entity.TransactionCurrency
import com.syarah.budgetmanagement.domain.entity.TransactionType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MonthLocalDataSource @Inject constructor(
    private val monthDao: MonthDao,
    private val transactionDao: TransactionDao,
) {

    suspend fun addMonth(month: Month) = monthDao.addMonth(month.toMonthDto())

    suspend fun updateMonth(month: Month) = monthDao.updateMonth(month.toMonthDto())

    suspend fun deleteMonth(month: Month) = monthDao.deleteMonth(month.toMonthDto())

    fun getMonths(accountId: Int): Flow<List<Month>> =
        monthDao.getMonths(accountId).map { dtoList ->
            withContext(Dispatchers.Default) {
                dtoList.toMonths().map { month -> calculateMontTransaction(month) }
            }
        }

    private suspend fun calculateMontTransaction(month: Month): Month {
        val transactions = transactionDao.getTransactionsByMonth(month.id)

        var dinarExpense = 0
        var dinarIncome = 0
        var dollarExpense = 0
        var dollarIncome = 0

        transactions.forEach { transaction ->
            when (transaction.currency) {
                TransactionCurrency.Dinar -> {
                    when (transaction.type) {
                        TransactionType.Expense -> dinarExpense += transaction.total
                        TransactionType.Income -> dinarIncome += transaction.total
                    }
                }

                TransactionCurrency.Dollar -> {
                    when (transaction.type) {
                        TransactionType.Expense -> dollarExpense += transaction.total
                        TransactionType.Income -> dollarIncome += transaction.total
                    }
                }
            }
        }

        return month.copy(
            dollarIncome = dollarIncome,
            dollarExpense = dollarExpense,
            dinarIncome = dinarIncome,
            dinarExpense = dinarExpense,
        )
    }

}