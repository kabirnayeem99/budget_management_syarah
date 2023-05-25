package com.syarah.budgetmanagement.data.service.database.converter

import androidx.room.TypeConverter
import com.syarah.budgetmanagement.domain.entity.TransactionCurrency
import com.syarah.budgetmanagement.domain.entity.TransactionType

class TransactionTypeConverter {
    @TypeConverter
    fun fromCurrency(currency: TransactionCurrency): String {
        return currency.name
    }

    @TypeConverter
    fun toCurrency(currencyName: String): TransactionCurrency {
        return enumValueOf(currencyName)
    }

    @TypeConverter
    fun fromType(type: TransactionType): String {
        return type.name
    }

    @TypeConverter
    fun toType(typeName: String): TransactionType {
        return enumValueOf(typeName)
    }
}
