package com.syarah.budgetmanagement.data.mapper

import com.syarah.budgetmanagement.data.dto.TransactionLocalDto
import com.syarah.budgetmanagement.domain.entity.Transaction
import com.syarah.budgetmanagement.domain.entity.TransactionDetails

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
            year = 0
        )
    }
}

fun Transaction.toTransactionDto(): TransactionLocalDto {
    val entity = this
    return TransactionLocalDto(
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

fun TransactionLocalDto.toTransactionDetails(): TransactionDetails {
    val entity = this
    return TransactionDetails(
        id = entity.id,
        date = entity.date,
        name = entity.name,
        monthId = entity.monthId,
        accountId = entity.accountId,
        amount = entity.total,
        currency = entity.currency,
        type = entity.type,
    )
}

fun TransactionDetails.toTransactionDto(): TransactionLocalDto {
    val entity = this
    return TransactionLocalDto(
        id = entity.id,
        date = entity.date,
        name = entity.name,
        monthId = entity.monthId,
        accountId = entity.accountId,
        total = entity.amount,
        currency = entity.currency,
        type = entity.type,
    )
}