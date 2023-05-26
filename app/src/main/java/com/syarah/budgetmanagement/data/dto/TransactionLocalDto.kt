package com.syarah.budgetmanagement.data.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.syarah.budgetmanagement.domain.entity.TransactionCurrency
import com.syarah.budgetmanagement.domain.entity.TransactionType
import java.util.Date

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = MonthLocalDto::class,
            parentColumns = ["id"],
            childColumns = ["monthId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AccountLocalDto::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TransactionLocalDto(
    @PrimaryKey val id: Int,
    val monthId: Int,
    val accountId: Int,
    val date: Date,
    val name: String,
    val total: Int,
    val currency: TransactionCurrency,
    val type: TransactionType,
)
