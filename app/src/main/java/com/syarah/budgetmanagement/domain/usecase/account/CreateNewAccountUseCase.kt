package com.syarah.budgetmanagement.domain.usecase.account

import com.syarah.budgetmanagement.domain.entity.Account
import com.syarah.budgetmanagement.domain.repository.AccountRepository
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

class CreateNewAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {

    suspend operator fun invoke(name: String): Result<Boolean> {
        return try {
            val newAccount = Account(
                id = UUID.randomUUID().mostSignificantBits.toInt(),
                name = name,
                dollarAmount = 0,
                dinarAmount = 0,
            )
            accountRepository.addAccount(newAccount)
            Result.success(true)
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }

    }
}