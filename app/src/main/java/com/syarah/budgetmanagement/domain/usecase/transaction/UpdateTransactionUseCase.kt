package com.syarah.budgetmanagement.domain.usecase.transaction

import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.repository.TransactionRepository
import javax.inject.Inject

class UpdateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(transaction: Transaction): Result<Unit> {
        return try {
            transactionRepository.updateTransaction(transaction)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}