package com.syarah.budgetmanagement.data.mapper

import com.syarah.budgetmanagement.data.dto.AccountLocalDto
import com.syarah.budgetmanagement.domain.entity.Account

fun List<AccountLocalDto>.toAccounts(): List<Account> {
    return map { dto ->
        Account(
            id = dto.id,
            name = dto.name,
            dollarAmount = dto.dollarAmount,
            dinarAmount = dto.dinarAmount,
        )
    }
}

fun List<Account>.toAccountDtoList(): List<AccountLocalDto> {
    return map { entity ->
        AccountLocalDto(
            id = entity.id,
            name = entity.name,
            dollarAmount = entity.dollarAmount,
            dinarAmount = entity.dinarAmount,
        )
    }
}
