package ir.one_developer.batmanmovies.ui.screen.detail_movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.util.DebugLogger
import ir.one_developer.batmanmovies.domain.Movie
import ir.one_developer.batmanmovies.ui.theme.BatmanMoviesTheme
import ir.one_developer.batmanmovies.utils.ext.loading

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@Composable
fun MovieDetailScreen(
    state: MovieDetailScreenState,
    onBack: () -> Unit
) = Box(
    modifier = Modifier.fillMaxSize()
) {
    val context = LocalContext.current
    val screenHeight = LocalConfiguration.current.screenHeightDp

    AsyncImage(
        model = state.movie.poster,
        contentScale = ContentScale.FillWidth,
        contentDescription = state.movie.title,
        modifier = Modifier.fillMaxWidth(),
        imageLoader = ImageLoader.Builder(context)
            .respectCacheHeaders(false)
            .logger(DebugLogger())
            .crossfade(300)
            .build()
    )

    IconButton(
        onClick = onBack,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = Icons.Rounded.Backspace.name
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = (screenHeight / 3).dp, max = (screenHeight / 1.5).dp)
            .align(Alignment.BottomCenter)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(color = MaterialTheme.colorScheme.background.copy(alpha = .9F))
            .padding(24.dp)
            .verticalScroll(state = rememberScrollState())
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        Text(
            text = state.movie.title.orEmpty(),
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.loading(visible = state.isLoading),
            text = "${state.movie.year}  ꞏ  ${state.movie.imdbRating}  ꞏ  ${state.movie.runtime}"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "${state.movie.plot}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.loading(visible = state.isLoading),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6F)
        )

        Spacer(modifier = Modifier.height(10.dp))

        RowText(
            isLoading = false,
            title = "Type:          ",
            text = state.movie.type?.replaceFirstChar { it.uppercase() }
        )

        RowText(
            title = "Country:     ",
            text = state.movie.country,
            isLoading = state.isLoading
        )

        RowText(
            title = "Language: ",
            text = state.movie.language,
            isLoading = state.isLoading
        )


        RowText(
            title = "Genre:        ",
            text = state.movie.genre,
            isLoading = state.isLoading
        )

        RowText(
            title = "Release:    ",
            text = state.movie.released,
            isLoading = state.isLoading
        )

        RowText(
            title = "Director:    ",
            text = state.movie.director,
            isLoading = state.isLoading
        )

        RowText(
            title = "Writer:        ",
            text = state.movie.writer,
            isLoading = state.isLoading
        )

        RowText(
            title = "Actors:       ",
            text = state.movie.actors,
            isLoading = state.isLoading
        )

        RowText(
            title = "BoxOffice:  ",
            isLoading = state.isLoading,
            text = state.movie.boxOffice
        )

    }

}

@Composable
private fun RowText(
    title: String,
    text: String?,
    isLoading: Boolean
) = Row {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6F)
    )
    Text(
        text = "$text",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.loading(visible = isLoading)
    )
}

@Preview(showBackground = true)
@Composable
private fun MoviesScreenPreview() = BatmanMoviesTheme {
    MovieDetailScreen(
        onBack = {},
        state = MovieDetailScreenState(movie = Movie.mockMovies.first())
    )
}