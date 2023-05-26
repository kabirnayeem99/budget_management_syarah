package com.syarah.budgetmanagement.domain.usecase.transaction

import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(
        accountId: Int,
        monthId: Int,
        yearId: Int,
    ): Flow<Result<List<Transaction>>> =
        transactionRepository.getTransactions(accountId, monthId, yearId).catch { error ->
            Timber.e(error)
            Result.failure<Exception>(error)
        }
            .map { transactions -> Result.success(transactions) }

}