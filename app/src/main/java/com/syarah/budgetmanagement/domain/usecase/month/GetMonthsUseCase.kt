package com.syarah.budgetmanagement.domain.usecase.month

import com.syarah.budgetmanagement.domain.entity.Month
import com.syarah.budgetmanagement.domain.repository.MonthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class GetMonthsUseCase @Inject constructor(
    private val monthRepository: MonthRepository,
) {
    operator fun invoke(accountId: Int): Flow<Result<List<Month>>> {
        return monthRepository.getMonths(accountId).catch { e ->
                Timber.e(e)
                Result.failure<Exception>(e)
            }.map { months -> Result.success(months) }
    }
}