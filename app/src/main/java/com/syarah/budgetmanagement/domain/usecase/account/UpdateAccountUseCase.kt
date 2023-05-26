package com.syarah.budgetmanagement.domain.usecase.account

import com.syarah.budgetmanagement.domain.entity.Account
import com.syarah.budgetmanagement.domain.repository.AccountRepository
import javax.inject.Inject

class UpdateAccountUseCase @Inject constructor(private val accountRepository: AccountRepository) {

    suspend operator fun invoke(account: Account): Result<String> {
        return try {
            accountRepository.updateAccount(account)
            return Result.success(account.name)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}