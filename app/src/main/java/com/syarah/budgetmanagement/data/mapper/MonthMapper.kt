package com.syarah.budgetmanagement.data.mapper

import com.syarah.budgetmanagement.data.dto.MonthLocalDto
import com.syarah.budgetmanagement.domain.entity.Month

fun List<MonthLocalDto>.toMonths(): List<Month> {
    return map { dto ->
        Month(
            id = dto.id,
            monthId = dto.monthId,
            year = dto.year,
            accountId = dto.accountId,
            presentableDate = "${dto.monthId}/${dto.year}"
        )
    }
}

fun Month.toMonthDto(): MonthLocalDto {
    return MonthLocalDto(
        id = this.id,
        monthId = this.monthId,
        year = this.year,
        accountId = this.accountId,
    )
}