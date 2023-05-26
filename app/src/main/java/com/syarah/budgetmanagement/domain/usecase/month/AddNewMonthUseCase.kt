package com.syarah.budgetmanagement.domain.usecase.month

import com.syarah.budgetmanagement.domain.entity.Month
import com.syarah.budgetmanagement.domain.repository.MonthRepository
import timber.log.Timber
import java.util.Calendar
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class AddNewMonthUseCase @Inject constructor(
    private val monthRepository: MonthRepository,
) {

    suspend operator fun invoke(month: Int, year: Int, accountId: Int): Result<String> {
        return try {
            val newMonth = Month(
                id = UUID.randomUUID().mostSignificantBits.toInt(),
                monthId = month,
                year = year,
                accountId = accountId
            )
            monthRepository.addMonth(newMonth)
            Result.success("${month}-${year}")
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }
    }
}