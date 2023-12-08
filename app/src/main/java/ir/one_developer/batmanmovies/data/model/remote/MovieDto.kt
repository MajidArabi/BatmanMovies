package ir.one_developer.batmanmovies.data.model.remote

import android.os.Parcelable
import com.squareup.moshi.Json
import ir.one_developer.batmanmovies.data.model.IMovie
import kotlinx.parcelize.Parcelize

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

data class MovieDto(
    @Json(name = "Title")
    override val title: String?,
    @Json(name = "Poster")
    override val poster: String?,
    @Json(name = "Type")
    override val type: String?,
    @Json(name = "Year")
    override val year: String?,
    @Json(name = "imdbID")
    override val imdbId: String
) : IMovie
