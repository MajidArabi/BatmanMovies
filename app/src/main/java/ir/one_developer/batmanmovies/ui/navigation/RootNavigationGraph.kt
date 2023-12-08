package ir.one_developer.batmanmovies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.one_developer.batmanmovies.ui.screen.detail_movie.MovieDetailScreen
import ir.one_developer.batmanmovies.ui.screen.detail_movie.MovieDetailViewModel
import ir.one_developer.batmanmovies.ui.screen.movies.MoviesScreen
import ir.one_developer.batmanmovies.ui.screen.movies.MoviesViewModel
import ir.one_developer.batmanmovies.ui.screen.splash.SplashScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController
) = NavHost(
    route = Graph.ROOT,
    navController = navController,
    startDestination = Destination.SplashScreen.route
) {

    composable(route = Destination.SplashScreen.route) {
        SplashScreen {
            navController.popBackStack()
            navController.navigate(Destination.MoviesScreen.route)
        }
    }

    composable(route = Destination.MoviesScreen.route) {
        val viewModel = hiltViewModel<MoviesViewModel>()
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        MoviesScreen(
            state = state,
            onMovieClicked = {
                navController.navigate(Destination.DetailScreen.route + "/${it.imdbId}")
            }
        )
    }

    composable(route = Destination.DetailScreen.route + "/{imdbId}") { navBackStack ->
        val imdbId = navBackStack.arguments?.getString("imdbId", "")
        val viewModel = hiltViewModel<MovieDetailViewModel>()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(key1 = imdbId) {
            if (!imdbId.isNullOrBlank()) {
                viewModel.getMovie(imdbId)
            }
        }

        MovieDetailScreen(
            state = state,
            onBack = { navController.popBackStack() }
        )
    }

}