package ir.one_developer.batmanmovies.ui.navigation

sealed class Destination(
    val route: String
) {
    data object MoviesScreen : Destination(route = "movies_screen")

    data object SplashScreen : Destination(route = "splash_screen")

    data object DetailScreen : Destination(route = "detail_screen")
}