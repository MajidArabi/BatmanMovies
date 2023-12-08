package ir.one_developer.batmanmovies.data.model

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

interface IMovie {
    val year: String?
    val type: String?
    val title: String?
    val imdbId: String
    val poster: String?
}