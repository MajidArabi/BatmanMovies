package ir.one_developer.batmanmovies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.one_developer.batmanmovies.data.model.local.MovieEntity

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        MovieEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}