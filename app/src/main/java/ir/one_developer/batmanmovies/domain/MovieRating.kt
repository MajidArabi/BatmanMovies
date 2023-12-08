package ir.one_developer.batmanmovies.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieRating(
    val source: String?,
    val value: String?
) : Parcelable