package com.syarah.budgetmanagement.data.mapper

import com.syarah.budgetmanagement.data.dto.AccountLocalDto
import com.syarah.budgetmanagement.domain.entity.Account

fun List<AccountLocalDto>.toAccounts(): List<Account> {
    return map { dto -> dto.toAccount() }
}

fun Account.toAccountDto(): AccountLocalDto {
    return AccountLocalDto(id, name)
}


fun AccountLocalDto.toAccount(): Account {
    return Account(id, name, 0, 0)
}