package ir.one_developer.batmanmovies.domain

import android.os.Parcelable
import ir.one_developer.batmanmovies.data.model.IMovie
import kotlinx.parcelize.Parcelize

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@Parcelize
data class Movie(
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
    override val imdbId: String = "",
    override val type: String? = null,
    override val year: String? = null,
    override val title: String? = null,
    override val poster: String? = null,
    val ratings: List<MovieRating>? = null
) : IMovie, Parcelable {

    companion object {
        val mockMovies = buildList {
            repeat(18) {
                add(
                    Movie(
                        year = "2005",
                        type = "movie",
                        imdbRating = "8.2",
                        runtime = "140 min",
                        language = "English",
                        title = "Batman Begins",
                        released = "15 Jun 2005",
                        country = "United States",
                        boxOffice = "$206,863,479",
                        genre = "Action, Crime, Drama",
                        director = "Christopher Nolan",
                        plot = "After witnessing his parents' death, Bruce learns the art of fighting to confront injustice. When he returns to Gotham as Batman, he must stop a secret society that intends to destroy the city.",
                    )
                )
            }
        }
    }

}