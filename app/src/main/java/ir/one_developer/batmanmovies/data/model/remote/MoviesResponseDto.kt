package ir.one_developer.batmanmovies.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@JsonClass(generateAdapter = true)
data class MoviesResponseDto(
    @Json(name = "Response")
    val response : String,
    val totalResults : String?,
    @Json(name = "Search")
    val movies : List<MovieDto>?
)
