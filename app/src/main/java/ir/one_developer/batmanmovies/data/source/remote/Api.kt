package ir.one_developer.batmanmovies.data.source.remote

import ir.one_developer.batmanmovies.data.model.remote.MovieDetailDto
import ir.one_developer.batmanmovies.data.model.remote.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

interface Api {

    interface V1 {

        @GET("/")
        suspend fun getMovies(
            @Query("s") searchText : String = "batman"
        ): MoviesResponseDto

        @GET("/")
        suspend fun getMovie(
            @Query("i") imdbId : String
        ): MovieDetailDto

    }

}