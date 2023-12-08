package ir.one_developer.batmanmovies.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.one_developer.batmanmovies.R
import ir.one_developer.batmanmovies.data.source.local.AppDatabase
import javax.inject.Singleton

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            context.getString(R.string.app_name).plus(".db")
        ).build()
    }

}