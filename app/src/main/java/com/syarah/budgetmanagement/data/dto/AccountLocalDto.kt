package com.syarah.budgetmanagement.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountLocalDto(
    @PrimaryKey val id: Int,
    val name: String,
    val dollarAmount: Int,
    val dinarAmount: Int,
)
