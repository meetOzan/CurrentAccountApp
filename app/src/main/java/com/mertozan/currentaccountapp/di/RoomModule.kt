package com.mertozan.currentaccountapp.di

import android.content.Context
import androidx.room.Room
import com.mertozan.currentaccountapp.common.Constants.DATABASE_NAME
import com.mertozan.currentaccountapp.data.local.CaDao
import com.mertozan.currentaccountapp.data.local.CaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CaDatabase {
        return Room.databaseBuilder(
            context,
            CaDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideUserDao(memDatabase: CaDatabase): CaDao {
        return memDatabase.caDao()
    }

}