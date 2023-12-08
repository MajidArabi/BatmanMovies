package ir.one_developer.batmanmovies.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ir.one_developer.batmanmovies.data.model.local.MovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertAll(movies: List<MovieEntity>)

    @Upsert
    suspend fun upsert(movie: MovieEntity)

    @Query("SELECT * FROM movie_table")
    fun getMovies(): List<MovieEntity>

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()

    @Query("DELETE FROM movie_table WHERE imdbId LIKE :movieImdbId")
    suspend fun delete(movieImdbId: String)

    @Query("SELECT * FROM movie_table WHERE imdbId LIKE :movieImdbId LIMIT 1")
    suspend fun getMovie(movieImdbId: String): MovieEntity?

}