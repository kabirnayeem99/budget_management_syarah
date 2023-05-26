package com.syarah.budgetmanagement.domain.usecase.account

import com.syarah.budgetmanagement.domain.repository.AccountRepository
import timber.log.Timber
import javax.inject.Inject

class GetSerialisedAccounts @Inject constructor(private val accountRepository: AccountRepository) {

    suspend operator fun invoke(): Result<String> {
        return try {
            val serialisedAccounts = accountRepository.getSerialisedAccounts()
            Result.success(serialisedAccounts)
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }
    }
}