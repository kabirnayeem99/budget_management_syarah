package com.syarah.budgetmanagement.data.mapper

import com.syarah.budgetmanagement.data.dto.MonthLocalDto
import com.syarah.budgetmanagement.domain.entity.Month

fun List<MonthLocalDto>.toMonths(): List<Month> {
    return map { dto ->
        Month(
            id = dto.id,
            monthId = dto.monthId,
            year = dto.year,
            presentableDate = dto.presentableDate,
            dollarExpense = dto.dollarExpense,
            dinarExpense = dto.dinarExpense,
            dollarIncome = dto.dollarIncome,
            dinarIncome = dto.dinarIncome,
            accountId = dto.accountId,
        )
    }
}

fun List<Month>.toMonthDtoList(): List<MonthLocalDto> {
    return map { entity ->
        MonthLocalDto(
            id = entity.id,
            monthId = entity.monthId,
            year = entity.year,
            presentableDate = entity.presentableDate,
            dollarExpense = entity.dollarExpense,
            dinarExpense = entity.dinarExpense,
            dollarIncome = entity.dollarIncome,
            dinarIncome = entity.dinarIncome,
            accountId = entity.accountId,
        )
    }
}