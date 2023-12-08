package ir.one_developer.batmanmovies.ui.screen.movies

import ir.one_developer.batmanmovies.domain.Movie

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

data class MoviesScreenState(
    val isLoading : Boolean = true,
    val movies : List<Movie> = if (isLoading) Movie.mockMovies else emptyList()
)