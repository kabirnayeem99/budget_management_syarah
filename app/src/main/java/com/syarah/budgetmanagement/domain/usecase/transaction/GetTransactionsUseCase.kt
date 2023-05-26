package com.syarah.budgetmanagement.domain.usecase.transaction

import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(): Flow<Result<List<Transaction>>> =
        transactionRepository.getTransactions().catch { error -> Result.failure<Exception>(error) }
            .map { transactions -> Result.success(transactions) }

}