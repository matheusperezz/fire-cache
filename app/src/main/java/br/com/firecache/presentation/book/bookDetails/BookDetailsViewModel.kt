package br.com.firecache.presentation.book.bookDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firecache.domain.entities.Book
import br.com.firecache.domain.usecases.BookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class BookDetailsUiState {
    object Loading : BookDetailsUiState()
    data class Success(val book: Book) : BookDetailsUiState()
    data class Error(val error: Throwable) : BookDetailsUiState()
    object NotFound : BookDetailsUiState()
}


@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val bookUseCase: BookUseCase
) : ViewModel() {

    private var currentUiStateJob: Job? = null
    private val _uiState = MutableStateFlow<BookDetailsUiState>(
        BookDetailsUiState.Loading
    )
    val uiState get() = _uiState

    fun fetchBook(bookId: String) {
        currentUiStateJob?.cancel()
        currentUiStateJob = viewModelScope.launch {
            try {
                bookUseCase.fetchById(bookId).collect { book ->
                    if (book != null) {
                        _uiState.value = BookDetailsUiState.Success(book)
                    } else {
                        _uiState.value = BookDetailsUiState.NotFound
                    }
                }
            } catch (e: Throwable) {
                _uiState.value = BookDetailsUiState.Error(e)
            }
        }
    }

}