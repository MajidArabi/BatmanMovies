package ir.one_developer.batmanmovies.data.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ir.one_developer.batmanmovies.data.model.IMovie

@JsonClass(generateAdapter = true)
data class MovieDetailDto(
    @Json(name = "DVD")
    val dvd: String?,
    @Json(name = "Actors")
    val actors: String?,
    @Json(name = "Awards")
    val awards: String?,
    @Json(name = "BoxOffice")
    val boxOffice: String?,
    @Json(name = "Country")
    val country: String?,
    @Json(name = "Director")
    val director: String?,
    @Json(name = "Genre")
    val genre: String?,
    val imdbRating: String?,
    val imdbVotes: String?,
    @Json(name = "Language")
    val language: String?,
    @Json(name = "Metascore")
    val metascore: String?,
    @Json(name = "Plot")
    val plot: String?,
    @Json(name = "Production")
    val production: String?,
    @Json(name = "Rated")
    val rated: String?,
    @Json(name = "Released")
    val released: String?,
    @Json(name = "Runtime")
    val runtime: String?,
    @Json(name = "Website")
    val website: String?,
    @Json(name = "Writer")
    val writer: String?,
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