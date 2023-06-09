package com.syarah.budgetmanagement.core.di

import com.syarah.budgetmanagement.data.datasource.localDataSource.AccountLocalDataSource
import com.syarah.budgetmanagement.data.datasource.localDataSource.MonthLocalDataSource
import com.syarah.budgetmanagement.data.datasource.remoteDataSource.AccountRemoteDataSource
import com.syarah.budgetmanagement.data.datasource.remoteDataSource.MonthRemoteDataSource
import com.syarah.budgetmanagement.data.datasource.remoteDataSource.TransactionRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {


    @Provides
    fun provideAccountRemoteDataSource(): AccountRemoteDataSource {
        return AccountRemoteDataSource()
    }



    @Provides
    fun provideMonthRemoteDataSource(): MonthRemoteDataSource {
        return MonthRemoteDataSource()
    }




    @Provides
    fun provideTransactionRemoteDataSource(): TransactionRemoteDataSource {
        return TransactionRemoteDataSource()
    }

}