package ir.one_developer.batmanmovies.data.repository

import androidx.room.withTransaction
import ir.one_developer.batmanmovies.data.mapper.toMovie
import ir.one_developer.batmanmovies.data.mapper.toMovieEntity
import ir.one_developer.batmanmovies.data.mapper.toMovies
import ir.one_developer.batmanmovies.data.mapper.toMoviesEntity
import ir.one_developer.batmanmovies.data.source.local.AppDatabase
import ir.one_developer.batmanmovies.data.source.remote.Api
import ir.one_developer.batmanmovies.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

class MovieRepositoryImpl @Inject constructor(
    private val api: Api.V1,
    private val appDatabase: AppDatabase
) : MovieRepository {

    override suspend fun getMovies(): Result<List<Movie>> = withContext(Dispatchers.IO) {
        try {
            val localMovies = appDatabase.movieDao.getMovies()
            if (localMovies.isNotEmpty()) {
                return@withContext Result.success(localMovies.toMovies())
            }
            val response = api.getMovies()
            val remoteMovies = response.movies
            appDatabase.withTransaction {
                appDatabase.movieDao.deleteAll()
                appDatabase.movieDao.upsertAll(remoteMovies.toMoviesEntity())
            }
            return@withContext Result.success(remoteMovies.toMoviesEntity().toMovies())
        } catch (e: Exception) {
            return@withContext Result.failure(e)
        }
    }

    override suspend fun getMovie(imdbId: String): Result<Movie> = withContext(Dispatchers.IO) {
        try {
            val localMovie = appDatabase.movieDao.getMovie(movieImdbId = imdbId)
            if (localMovie?.released != null) {
                return@withContext Result.success(localMovie.toMovie())
            }
            val response = api.getMovie(imdbId = imdbId)
            val movieEntity = response.toMovieEntity()
            appDatabase.withTransaction {
                appDatabase.movieDao.delete(movieImdbId = imdbId)
                appDatabase.movieDao.upsert(movieEntity)
            }
            return@withContext Result.success(movieEntity.toMovie())
        } catch (e: Exception) {
            return@withContext Result.failure(e)
        }
    }

}