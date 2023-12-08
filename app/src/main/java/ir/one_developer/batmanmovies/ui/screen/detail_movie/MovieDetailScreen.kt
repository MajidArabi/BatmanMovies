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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
    val colors = MaterialTheme.colorScheme
    val screenHeight = LocalConfiguration.current.screenHeightDp

    Box {
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

        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, colors.background)
                    )
                )
        )
    }

    IconButton(
        onClick = onBack,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .background(
                color = colors.background.copy(alpha = 0.6F),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = Icons.Rounded.Backspace.name,
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = (screenHeight / 3).dp, max = (screenHeight / 1.8).dp)
            .align(Alignment.BottomCenter)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(color = colors.background)
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp)
            .navigationBarsPadding()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        Text(
            text = "${state.movie.title}",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.loading(visible = state.isLoading),
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
            color = colors.onBackground.copy(alpha = 0.6F)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (!state.movie.type.isNullOrBlank()) Title(title = "Type:")
                if (!state.movie.country.isNullOrBlank()) Title(title = "Country:")
                if (!state.movie.language.isNullOrBlank()) Title(title = "Language:")
                if (!state.movie.genre.isNullOrBlank()) Title(title = "Genre:")
                if (!state.movie.released.isNullOrBlank()) Title(title = "Release:")
                if (!state.movie.boxOffice.isNullOrBlank()) Title(title = "BoxOffice:")
                if (!state.movie.director.isNullOrBlank()) Title(title = "Director:")
                if (!state.movie.writer.isNullOrBlank()) Title(title = "Writer:")
                if (!state.movie.actors.isNullOrBlank()) Title(title = "Actors:")
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (!state.movie.type.isNullOrBlank()) Description(
                    isLoading = state.isLoading,
                    text = state.movie.type.replaceFirstChar { it.uppercase() }
                )

                if (!state.movie.country.isNullOrBlank()) Description(
                    text = state.movie.country,
                    isLoading = state.isLoading
                )

                if (!state.movie.language.isNullOrBlank()) Description(
                    text = state.movie.language,
                    isLoading = state.isLoading
                )

                if (!state.movie.genre.isNullOrBlank()) Description(
                    text = state.movie.genre,
                    isLoading = state.isLoading
                )

                if (!state.movie.released.isNullOrBlank()) Description(
                    text = state.movie.released,
                    isLoading = state.isLoading
                )

                if (!state.movie.boxOffice.isNullOrBlank()) Description(
                    isLoading = state.isLoading,
                    text = state.movie.boxOffice
                )

                if (!state.movie.director.isNullOrBlank()) Description(
                    text = state.movie.director,
                    isLoading = state.isLoading
                )

                if (!state.movie.writer.isNullOrBlank()) Description(
                    text = state.movie.writer,
                    isLoading = state.isLoading
                )

                if (!state.movie.actors.isNullOrBlank()) Description(
                    text = state.movie.actors,
                    isLoading = state.isLoading
                )
            }
        }

    }

}

@Composable
private fun Title(
    title: String,
) = Text(
    text = title,
    style = MaterialTheme.typography.titleMedium,
    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6F)
)

@Composable
private fun Description(
    text: String?,
    isLoading: Boolean
) = Text(
    text = "$text",
    style = MaterialTheme.typography.titleMedium,
    modifier = Modifier.loading(visible = isLoading)
)

@Preview(showBackground = true)
@Composable
private fun MoviesScreenPreview() = BatmanMoviesTheme {
    MovieDetailScreen(
        onBack = {},
        state = MovieDetailScreenState(movie = Movie.mockMovies.first())
    )
}