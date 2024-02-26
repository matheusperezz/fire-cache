package br.com.firecache.presentation.book.bookList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firecache.domain.entities.Book
import br.com.firecache.domain.usecases.BookUseCase
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
    private val bookUseCase: BookUseCase
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
                // Local
                bookUseCase.fetchAll().collect { books ->
                    if (books.isEmpty()) {
                        _uiState.value = BookListUiState.Empty
                    } else {
                        _uiState.value = BookListUiState.Success(books)
                    }
                }
                // Remote
            } catch (e: Throwable) {
                _uiState.value = BookListUiState.Error(e)
            }
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            bookUseCase.delete(book)
        }
    }
}