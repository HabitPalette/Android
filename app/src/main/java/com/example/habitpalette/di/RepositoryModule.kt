package com.example.habitpalette.di

import android.content.Context
import androidx.room.Room
import com.example.habitpalette.data.persistence.AppDatabase
import com.example.habitpalette.data.persistence.HabitDao
import com.example.habitpalette.data.persistence.HabitHistoryDao
import com.example.habitpalette.data.repository.HabitHistoryRepository
import com.example.habitpalette.data.repository.HabitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides 
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "habit_database"
        ).fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideHabitRepository(habitDao: HabitDao): HabitRepository{
        return HabitRepository(habitDao)
    }

    @Provides
    fun provideHabitDao(appDatabase: AppDatabase) = appDatabase.HabitDao()

    @Singleton
    @Provides
    fun provideHabitHistoryRepository(habitHistoryDao: HabitHistoryDao): HabitHistoryRepository{
        return HabitHistoryRepository(habitHistoryDao)
    }
}