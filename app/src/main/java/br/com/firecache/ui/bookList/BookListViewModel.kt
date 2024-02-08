package br.com.firecache.ui.bookList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firecache.data.models.Book
import br.com.firecache.data.repositories.BookRepository
import br.com.firecache.data.repositories.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
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

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            bookRepository.delete(book)
        }
    }
}