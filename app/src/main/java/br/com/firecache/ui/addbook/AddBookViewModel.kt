package br.com.firecache.ui.addbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firecache.data.models.Genre
import br.com.firecache.data.repositories.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class AddBookFormUiState(
    val title: String = "",
    val author: String = "",
    val imageUrl: String = "",
    val topic: String = "",

    val onTitleChange: (String) -> Unit = {},
    val onAuthorChange: (String) -> Unit = {},
    val onImageUrlChange: (String) -> Unit = {},
    val onTopicChange: (String) -> Unit = {},

    val selectedGenre: Genre? = null,
    val isShowGenreBottomSheet: Boolean = false,
)

@HiltViewModel
class AddBookViewModel @Inject constructor(private val genreRepository: GenreRepository) :
    ViewModel() {

    private val _uiState: MutableStateFlow<AddBookFormUiState> =
        MutableStateFlow(AddBookFormUiState())
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    init {
        setupFormFields()
    }

    fun toggleBottomSheet() {
        _uiState.value =
            _uiState.value.copy(isShowGenreBottomSheet = !_uiState.value.isShowGenreBottomSheet)
    }

    private fun setupFormFields() {
        _uiState.value = AddBookFormUiState(
            onTitleChange = { title -> _uiState.value = _uiState.value.copy(title = title) },
            onAuthorChange = { author -> _uiState.value = _uiState.value.copy(author = author) },
            onImageUrlChange = { imageUrl ->
                _uiState.value = _uiState.value.copy(imageUrl = imageUrl)
            },
            onTopicChange = { topic -> _uiState.value = _uiState.value.copy(topic = topic) }
        )
    }

}