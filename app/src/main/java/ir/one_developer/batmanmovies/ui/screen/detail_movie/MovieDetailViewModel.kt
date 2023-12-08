package ir.one_developer.batmanmovies.ui.screen.detail_movie

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
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailScreenState())
    val uiState = _uiState.asStateFlow()

    fun getMovie(imdbId: String) = viewModelScope.launch {
        movieRepository.getMovie(imdbId = imdbId).onSuccess { movie ->
            _uiState.update {
                it.copy(
                    movie = movie,
                    isLoading = false
                )
            }
        }.onFailure {
            // send error to ui
            Log.e("MOVIE", "$it")
        }
    }

}