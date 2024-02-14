package br.com.firecache.ui.genre.createGenre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firecache.data.models.Genre
import br.com.firecache.data.repositories.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreateGenreUiState(
    val name: String = "",
    val onNameChange: (String) -> Unit = {}
)

@HiltViewModel
class CreateGenreViewModel @Inject constructor(
    private val genreRepository: GenreRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<CreateGenreUiState> =
        MutableStateFlow(CreateGenreUiState())
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    init {
        setupFormFields()
    }

    private fun setupFormFields() {
        _uiState.value = CreateGenreUiState(
            onNameChange = { name -> _uiState.value = _uiState.value.copy(name = name) }
        )
    }

    fun createGenre(
        name: String
    ){
        viewModelScope.launch {
            val genre = Genre(name = name)
            genreRepository.insert(genre)
        }
    }

}