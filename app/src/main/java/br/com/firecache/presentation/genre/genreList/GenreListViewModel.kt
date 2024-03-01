package br.com.firecache.presentation.genre.genreList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firecache.domain.entities.Genre
import br.com.firecache.domain.usecases.GenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class GenreListUiState {
    object Loading : GenreListUiState()
    data class Success(val genres: List<Genre>) : GenreListUiState()
    data class Error(val error: Throwable) : GenreListUiState()
    object Empty : GenreListUiState()
}

@HiltViewModel
class GenreListViewModel @Inject constructor(
    private val genreUseCase: GenreUseCase
): ViewModel(){

    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<GenreListUiState>(
        GenreListUiState.Loading
    )
    val uiState = _uiState

    init {
        syncData()
        fetchGenres()
    }

    private fun fetchGenres(){
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            _uiState.value = GenreListUiState.Loading
            try {
                genreUseCase.fetchAll().collect {
                    if (it.isEmpty()) {
                        _uiState.value = GenreListUiState.Empty
                    } else {
                        _uiState.value = GenreListUiState.Success(it)
                    }
                }
            } catch (e: Throwable) {
                _uiState.value = GenreListUiState.Error(e)
            }
        }
    }

    private fun syncData(){
        viewModelScope.launch {
            genreUseCase.syndData()
        }
    }
}