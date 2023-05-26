package com.syarah.budgetmanagement.domain.usecase.transaction

import com.syarah.budgetmanagement.domain.entity.TransactionDetails
import com.syarah.budgetmanagement.domain.repository.TransactionRepository
import timber.log.Timber
import javax.inject.Inject

class GetTransactionDetailsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(id: Int): Result<TransactionDetails> {
        return try {
            val transactionDetails = transactionRepository.getTransactionDetails(id)
            Result.success(transactionDetails)
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }
    }
}