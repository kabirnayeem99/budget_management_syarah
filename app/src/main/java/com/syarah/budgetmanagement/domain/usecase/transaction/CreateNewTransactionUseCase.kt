package com.syarah.budgetmanagement.domain.usecase.transaction

import com.syarah.budgetmanagement.domain.entity.TransactionCurrency
import com.syarah.budgetmanagement.domain.entity.TransactionDetails
import com.syarah.budgetmanagement.domain.entity.TransactionType
import com.syarah.budgetmanagement.domain.repository.TransactionRepository
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

class CreateNewTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke(
        transactionName: String,
        amount: Int,
        type: TransactionType,
        currency: TransactionCurrency,
        accountId: Int,
        monthId: Int,
        year: Int,
    ): Result<Unit> {
        return try {
            val transaction = TransactionDetails(
                id= UUID.randomUUID().mostSignificantBits.toInt(),
                name = transactionName,
                accountId = accountId,
                monthId = monthId,
                year = year,
                currency = currency,
                type = type,
                amount = amount,
            )
            Timber.d(transaction.toString())
            transactionRepository.addTransaction(transaction)
            Result.success(Unit)
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }
    }
}