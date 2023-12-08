package ir.one_developer.batmanmovies.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.one_developer.batmanmovies.data.model.IMovie

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@Entity(tableName = "movie_table")
data class MovieEntity(
    val dvd: String? = null,
    val plot: String? = null,
    val rated: String? = null,
    val genre: String? = null,
    val actors: String? = null,
    val awards: String? = null,
    val writer: String? = null,
    val country: String? = null,
    val runtime: String? = null,
    val website: String? = null,
    val director: String? = null,
    val language: String? = null,
    val released: String? = null,
    val imdbVotes: String? = null,
    val boxOffice: String? = null,
    val metascore: String? = null,
    val production: String? = null,
    val imdbRating: String? = null,
    override val type: String?,
    override val year: String?,
    override val title: String?,
    override val poster: String?,
    @PrimaryKey override val imdbId: String,
) : IMovie