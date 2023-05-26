package com.syarah.budgetmanagement.domain.usecase.transaction

import com.syarah.budgetmanagement.domain.entity.TransactionDetails
import com.syarah.budgetmanagement.domain.repository.TransactionRepository
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(transaction: TransactionDetails): Result<Unit> {
        return try {
            transactionRepository.addTransaction(transaction)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}