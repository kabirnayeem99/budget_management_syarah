package com.syarah.budgetmanagement.core.di

import com.syarah.budgetmanagement.data.datasource.localDataSource.AccountLocalDataSource
import com.syarah.budgetmanagement.data.datasource.localDataSource.MonthLocalDataSource
import com.syarah.budgetmanagement.data.datasource.localDataSource.TransactionLocalDataSource
import com.syarah.budgetmanagement.data.dto.TransactionLocalDto
import com.syarah.budgetmanagement.data.repository.DefaultAccountRepository
import com.syarah.budgetmanagement.data.repository.DefaultMonthRepository
import com.syarah.budgetmanagement.data.repository.DefaultTransactionRepository
import com.syarah.budgetmanagement.data.service.database.dao.TransactionDao
import com.syarah.budgetmanagement.domain.repository.AccountRepository
import com.syarah.budgetmanagement.domain.repository.MonthRepository
import com.syarah.budgetmanagement.domain.repository.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAccountRepository(
        accountLocalDataSource: AccountLocalDataSource
    ): AccountRepository {
        return DefaultAccountRepository(accountLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideMonthRepository(
        monthLocalDataSource: MonthLocalDataSource
    ): MonthRepository {
        return DefaultMonthRepository(monthLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(transactionLocalDataSource: TransactionLocalDataSource): TransactionRepository {
        return DefaultTransactionRepository(transactionLocalDataSource)
    }
}