package br.com.firecache.ui.book.bookList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firecache.data.models.Book
import br.com.firecache.data.models.Genre
import br.com.firecache.data.repositories.BookRepository
import br.com.firecache.data.repositories.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class BookListUiState {
    object Loading : BookListUiState()
    data class Success(val books: List<Book>) : BookListUiState()
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
                bookRepository.fetchAll().collect { books ->
                    if (books.isEmpty()) {
                        _uiState.value = BookListUiState.Empty
                    } else {
                        _uiState.value = BookListUiState.Success(books)
                    }
                }
            } catch (e: Throwable) {
                _uiState.value = BookListUiState.Error(e)
            }
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            bookRepository.delete(book)
        }
    }
}