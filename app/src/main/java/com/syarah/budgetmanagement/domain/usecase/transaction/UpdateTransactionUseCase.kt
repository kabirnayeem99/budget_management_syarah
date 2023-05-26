package com.syarah.budgetmanagement.domain.usecase.transaction

import com.syarah.budgetmanagement.domain.entity.TransactionCurrency
import com.syarah.budgetmanagement.domain.entity.TransactionDetails
import com.syarah.budgetmanagement.domain.entity.TransactionType
import com.syarah.budgetmanagement.domain.repository.TransactionRepository
import javax.inject.Inject

class UpdateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {

    suspend operator fun invoke(
        transaction: TransactionDetails,
    ): Result<Unit> {
        return try {
            transactionRepository.updateTransaction(transaction)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}