package br.com.firecache.ui.bookList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firecache.data.mathBooks
import br.com.firecache.data.models.BookCardModel
import br.com.firecache.data.models.Genre
import br.com.firecache.data.repositories.BookRepository
import br.com.firecache.data.repositories.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class BookListUiState {
    object Loading : BookListUiState()
    data class Success(val books: List<BookCardModel>) : BookListUiState()
    data class Error(val error: Throwable) : BookListUiState()
    object Empty : BookListUiState()
}

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val genreRepository: GenreRepository
) : ViewModel() {
    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<BookListUiState>(
        BookListUiState.Loading
    )
    val uiState = _uiState

    init {
        fetchBooks()
    }

    private fun fetchBooks() {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            _uiState.value = BookListUiState.Loading
            try {
                bookRepository.fetchAll().collect {
                    if (it.isEmpty()) {
                        _uiState.value = BookListUiState.Empty
                    } else {
                        _uiState.value = BookListUiState.Success(it)
                    }
                }
            } catch (e: Throwable) {
                _uiState.value = BookListUiState.Error(e)
            }
        }
    }

    fun onAddBookClick() {

        val rand = (0..mathBooks.size).random()

        val book = BookCardModel(
            title = mathBooks[rand].title,
            author = mathBooks[rand].author,
            genreId = "9d9cfbd2-d1e6-484c-9eca-fb95eb91bb63",
            imageUrl = mathBooks[rand].imageUrl,
            topic = mathBooks[rand].topic,
        )
        viewModelScope.launch {
            bookRepository.insert(book)
        }
    }
}