package br.com.firecache.presentation.book.createBook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firecache.data.models.Book
import br.com.firecache.data.models.Genre
import br.com.firecache.data.repositories.BookRepository
import br.com.firecache.data.repositories.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreateBookUiState(
    val title: String = "",
    val author: String = "",
    val imageUrl: String = "",
    val topic: String = "",
    val comments: String = "",

    val onTitleChange: (String) -> Unit = {},
    val onAuthorChange: (String) -> Unit = {},
    val onImageUrlChange: (String) -> Unit = {},
    val onTopicChange: (String) -> Unit = {},
    val onCommentsChange: (String) -> Unit = {},

    val selectedGenre: Genre? = null,
    val isShowGenreBottomSheet: Boolean = false,
    val genreList: List<Genre> = emptyList()
)

@HiltViewModel
class CreateBookViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val bookRepository: BookRepository
) :
    ViewModel() {

    private val _uiState: MutableStateFlow<CreateBookUiState> =
        MutableStateFlow(CreateBookUiState())
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = _uiState.value
    )

    init {
        setupFormFields()
        fetchGenres()
    }

    fun toggleBottomSheet() {
        _uiState.value =
            _uiState.value.copy(isShowGenreBottomSheet = !_uiState.value.isShowGenreBottomSheet)
    }

    private fun setupFormFields() {
        _uiState.value = CreateBookUiState(
            onTitleChange = { title -> _uiState.value = _uiState.value.copy(title = title) },
            onAuthorChange = { author -> _uiState.value = _uiState.value.copy(author = author) },
            onImageUrlChange = { imageUrl ->
                _uiState.value = _uiState.value.copy(imageUrl = imageUrl)
            },
            onTopicChange = { topic -> _uiState.value = _uiState.value.copy(topic = topic) },
            onCommentsChange = { comments ->
                _uiState.value = _uiState.value.copy(comments = comments)
            }
        )
    }

    private fun fetchGenres() {
        viewModelScope.launch {
            genreRepository.fetchAllGenres().collect {
                _uiState.value = _uiState.value.copy(genreList = it)
            }
        }
    }

    fun selectGenre(selectedGenre: Genre) {
        _uiState.value = _uiState.value.copy(selectedGenre = selectedGenre)
    }

    fun createBook(book: Book) {
        viewModelScope.launch {
            bookRepository.insert(book)
        }
    }

    fun updateBook(book: Book) {
        viewModelScope.launch {
            bookRepository.update(book)
        }
    }

}