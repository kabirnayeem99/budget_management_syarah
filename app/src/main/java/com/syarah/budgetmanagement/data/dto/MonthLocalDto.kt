package com.syarah.budgetmanagement.data.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "months",
    foreignKeys = [
        ForeignKey(
            entity = AccountLocalDto::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class MonthLocalDto(
    @PrimaryKey val id: Int,
    val accountId: Int,
    val monthId: Int,
    val year: Int,

)
