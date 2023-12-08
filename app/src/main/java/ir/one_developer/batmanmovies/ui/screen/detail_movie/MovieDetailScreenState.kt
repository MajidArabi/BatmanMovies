package ir.one_developer.batmanmovies.ui.screen.detail_movie

import ir.one_developer.batmanmovies.domain.Movie

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

data class MovieDetailScreenState(
    val movie : Movie = Movie(),
    val isLoading : Boolean = true
)