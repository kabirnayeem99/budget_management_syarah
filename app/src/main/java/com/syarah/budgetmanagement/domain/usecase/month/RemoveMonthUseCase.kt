package com.syarah.budgetmanagement.domain.usecase.month

import com.syarah.budgetmanagement.domain.entity.Month
import com.syarah.budgetmanagement.domain.repository.MonthRepository
import javax.inject.Inject

class RemoveMonthUseCase @Inject constructor(
    private val monthRepository: MonthRepository,
) {
    suspend operator fun invoke(month: Month): Result<String> {
        return try {
            monthRepository.deleteMonth(month)
            Result.success(month.presentableDate)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}