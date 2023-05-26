package com.syarah.budgetmanagement.core.di

import android.content.Context
import androidx.room.Room
import com.syarah.budgetmanagement.data.service.database.BudgetManagementDatabase
import com.syarah.budgetmanagement.data.service.database.dao.AccountDao
import com.syarah.budgetmanagement.data.service.database.dao.MonthDao
import com.syarah.budgetmanagement.data.service.database.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BudgetManagementDatabase {
        return Room.databaseBuilder(
            context, BudgetManagementDatabase::class.java, "budget_management_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideAccountDao(db: BudgetManagementDatabase): AccountDao = db.getAccountDao()

    @Provides
    fun provideMonthDao(db: BudgetManagementDatabase): MonthDao = db.getMonthDao()

    @Provides
    fun provideTransactionDao(db: BudgetManagementDatabase): TransactionDao = db.getTransactionDao()


}