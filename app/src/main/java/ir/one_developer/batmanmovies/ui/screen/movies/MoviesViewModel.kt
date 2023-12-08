package ir.one_developer.batmanmovies.ui.screen.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.one_developer.batmanmovies.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by majid on 12/7/23.
 * Copyright (c) 2023 majid. All rights reserved.
 */

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MoviesScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.getMovies().onSuccess { movies ->
                _uiState.update {
                    it.copy(
                        movies = movies,
                        isLoading = false
                    )
                }
            }.onFailure {
                // send error to ui
                Log.e("MOVIES", "$it")
            }
        }
    }

}