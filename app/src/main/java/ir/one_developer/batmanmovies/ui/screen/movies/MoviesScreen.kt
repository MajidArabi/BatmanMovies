package ir.one_developer.batmanmovies.ui.screen.movies

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.util.DebugLogger
import ir.one_developer.batmanmovies.R
import ir.one_developer.batmanmovies.domain.Movie
import ir.one_developer.batmanmovies.ui.theme.BatmanMoviesTheme
import ir.one_developer.batmanmovies.utils.ext.loading

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@Composable
fun MoviesScreen(
    state: MoviesScreenState,
    onMovieClicked: (movie: Movie) -> Unit
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .navigationBarsPadding()
) {

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(130.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(3) }) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(R.drawable.logo)
                )

                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    text = stringResource(R.string.app_name).uppercase()
                )
            }
        }
        items(state.movies) {
            MovieItem(
                movie = it,
                isLoading = state.isLoading
            ) {
                onMovieClicked(it)
            }
        }
    }

}

@Composable
private fun MovieItem(
    movie: Movie,
    isLoading: Boolean,
    onMovieClick: () -> Unit
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(4.dp),
    modifier = Modifier
        .wrapContentHeight()
        .padding(4.dp)
        .clip(shape = MaterialTheme.shapes.medium)
        .clickable { onMovieClick() }
) {
    val context = LocalContext.current

    AsyncImage(
        model = movie.poster,
        contentDescription = movie.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(160.dp)
            .clip(shape = MaterialTheme.shapes.small)
            .loading(visible = isLoading),
        imageLoader = ImageLoader.Builder(context)
            .respectCacheHeaders(false)
            .logger(DebugLogger())
            .crossfade(300)
            .build()
    )

    Text(
        maxLines = 1,
        text = movie.title.orEmpty(),
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.small)
            .loading(visible = isLoading)
    )

    Text(
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.labelSmall,
        text = "${movie.year} ꞏ ${movie.type?.uppercase()}",
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .4f),
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.small)
            .loading(visible = isLoading)
    )
}

@Preview(showBackground = true)
@Composable
private fun MoviesScreenPreview() = BatmanMoviesTheme {
    MoviesScreen(
        state = MoviesScreenState(),
        onMovieClicked = {}
    )
}

@Preview(name = "Light", showBackground = true)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun MovieItemPreview() = BatmanMoviesTheme {
    MovieItem(
        isLoading = true,
        onMovieClick = {},
        movie = Movie.mockMovies.first()
    )
}