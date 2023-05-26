package com.syarah.budgetmanagement.domain.usecase.month

import com.syarah.budgetmanagement.domain.entity.Month
import com.syarah.budgetmanagement.domain.repository.MonthRepository
import javax.inject.Inject

class UpdateMonthInfoUseCase @Inject constructor(
    private val monthRepository: MonthRepository,
) {
    suspend operator fun invoke(month: Month): Result<String> {
        return try {
            monthRepository.updateMonth(month)
            Result.success(month.presentableDate)
        } catch (e:Exception) {
            Result.failure(e)
        }
    }
}