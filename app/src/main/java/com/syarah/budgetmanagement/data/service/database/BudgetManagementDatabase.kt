package com.syarah.budgetmanagement.data.service.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.syarah.budgetmanagement.data.dto.AccountLocalDto
import com.syarah.budgetmanagement.data.dto.MonthLocalDto
import com.syarah.budgetmanagement.data.dto.TransactionLocalDto
import com.syarah.budgetmanagement.data.service.database.converter.TransactionTypeConverter
import com.syarah.budgetmanagement.data.service.database.dao.AccountDao
import com.syarah.budgetmanagement.data.service.database.dao.MonthDao
import com.syarah.budgetmanagement.data.service.database.dao.TransactionDao

@Database(
    entities = [AccountLocalDto::class, MonthLocalDto::class, TransactionLocalDto::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(TransactionTypeConverter::class)
abstract class BudgetManagementDatabase : RoomDatabase() {
    abstract fun getAccountDao(): AccountDao
    abstract fun getMonthDao(): MonthDao
    abstract fun getTransactionDao(): TransactionDao
}