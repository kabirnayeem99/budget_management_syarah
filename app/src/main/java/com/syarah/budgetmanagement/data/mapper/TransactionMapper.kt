package com.syarah.budgetmanagement.data.mapper

import com.syarah.budgetmanagement.data.dto.TransactionLocalDto
import com.syarah.budgetmanagement.domain.entity.Transaction

fun List<TransactionLocalDto>.toTransactions(): List<Transaction> {
    return map { dto ->
        Transaction(
            id = dto.id,
            date = dto.date,
            name = dto.name,
            monthId = dto.monthId,
            accountId = dto.accountId,
            total = dto.total,
            currency = dto.currency,
            type = dto.type,

            )
    }
}

fun List<Transaction>.toTransactionDtoList(): List<TransactionLocalDto> {
    return map { entity ->
        TransactionLocalDto(
            id = entity.id,
            date = entity.date,
            name = entity.name,
            monthId = entity.monthId,
            accountId = entity.accountId,
            total = entity.total,
            currency = entity.currency,
            type = entity.type,
        )
    }
}