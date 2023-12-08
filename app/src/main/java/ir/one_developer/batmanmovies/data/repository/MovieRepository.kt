package ir.one_developer.batmanmovies.data.repository

import ir.one_developer.batmanmovies.domain.Movie

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

interface MovieRepository {
    suspend fun getMovies(): Result<List<Movie>>
    suspend fun getMovie(imdbId: String): Result<Movie>
}