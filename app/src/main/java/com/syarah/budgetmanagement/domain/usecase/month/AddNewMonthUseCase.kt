package com.syarah.budgetmanagement.domain.usecase.month

import com.syarah.budgetmanagement.domain.entity.Month
import com.syarah.budgetmanagement.domain.repository.MonthRepository
import java.util.Calendar
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class AddNewMonthUseCase @Inject constructor(
    private val monthRepository: MonthRepository,
) {

    suspend operator fun invoke(month: Int, year: Int): Result<String> {
        return try {
            val newMonth = Month(
                id = UUID.randomUUID().mostSignificantBits.toInt(),
                monthId = month,
                year = year,
            )
            monthRepository.addMonth(newMonth)
            Result.success("${month}-${year}")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}