package com.syarah.budgetmanagement.core.di

import com.syarah.budgetmanagement.data.datasource.localDataSource.AccountLocalDataSource
import com.syarah.budgetmanagement.data.datasource.localDataSource.MonthLocalDataSource
import com.syarah.budgetmanagement.data.repository.DefaultAccountRepository
import com.syarah.budgetmanagement.data.repository.DefaultMonthRepository
import com.syarah.budgetmanagement.domain.repository.AccountRepository
import com.syarah.budgetmanagement.domain.repository.MonthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideAccountRepository(
        accountLocalDataSource: AccountLocalDataSource
    ): AccountRepository {
        return DefaultAccountRepository(accountLocalDataSource)
    }

    @Provides
    fun provideMonthRepository(
        monthLocalDataSource: MonthLocalDataSource
    ): MonthRepository {
        return DefaultMonthRepository(monthLocalDataSource)
    }
}