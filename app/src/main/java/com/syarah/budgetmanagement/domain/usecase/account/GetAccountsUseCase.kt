package com.syarah.budgetmanagement.domain.usecase.account

import com.syarah.budgetmanagement.domain.entity.Account
import com.syarah.budgetmanagement.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {

    operator fun invoke(): Flow<Result<List<Account>>> {
        return accountRepository.getAccounts()
            .catch { error ->
                Timber.e(error)
                Result.failure<Exception>(Exception(error))
            }
            .map { accounts -> Result.success(accounts) }
    }
}