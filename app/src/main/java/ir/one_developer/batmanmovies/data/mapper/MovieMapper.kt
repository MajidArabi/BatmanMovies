package ir.one_developer.batmanmovies.data.mapper

import ir.one_developer.batmanmovies.data.model.local.MovieEntity
import ir.one_developer.batmanmovies.data.model.remote.MovieDetailDto
import ir.one_developer.batmanmovies.data.model.remote.MovieDto
import ir.one_developer.batmanmovies.domain.Movie

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        year = this.year,
        type = this.type,
        title = this.title,
        poster = this.poster,
        imdbId = this.imdbId
    )
}

fun MovieDetailDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        year = this.year,
        type = this.type,
        title = this.title,
        poster = this.poster,
        imdbId = this.imdbId,
        dvd = this.dvd,
        plot = this.plot,
        genre = this.genre,
        rated = this.rated,
        actors = this.actors,
        awards = this.awards,
        writer = this.writer,
        country = this.country,
        runtime = this.runtime,
        website = this.website,
        director = this.director,
        language = this.language,
        released = this.released,
        imdbVotes = this.imdbVotes,
        boxOffice = this.boxOffice,
        metascore = this.metascore,
        imdbRating = this.imdbRating,
        production = this.production,
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        year = this.year,
        type = this.type,
        title = this.title,
        poster = this.poster,
        imdbId = this.imdbId,
        dvd = this.dvd,
        plot = this.plot,
        genre = this.genre,
        rated = this.rated,
        actors = this.actors,
        awards = this.awards,
        writer = this.writer,
        country = this.country,
        runtime = this.runtime,
        website = this.website,
        director = this.director,
        language = this.language,
        released = this.released,
        imdbVotes = this.imdbVotes,
        boxOffice = this.boxOffice,
        metascore = this.metascore,
        imdbRating = this.imdbRating,
        production = this.production,
    )
}

fun List<MovieDto>?.toMoviesEntity(): List<MovieEntity> {
    return this?.map { it.toMovieEntity() }.orEmpty()
}

fun List<MovieEntity>?.toMovies(): List<Movie> {
    return this?.map { it.toMovie() }.orEmpty()
}