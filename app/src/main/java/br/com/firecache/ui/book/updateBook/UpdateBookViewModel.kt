package br.com.firecache.ui.book.updateBook

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firecache.data.models.Book
import br.com.firecache.data.repositories.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UpdateBookUiState(
    val title: String = "",
    val author: String = "",
    val genre: String = ""
)

@HiltViewModel
class UpdateBookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UpdateBookUiState())
    val uiState get() = _uiState

    init {
        // TODO: Receber o id do livro a ser atualizado de alguma forma :|
        loadBook("e4fbc6a4-332e-4e41-889d-17506725b026")
    }

    fun onTitleChange(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun onAuthorChange(author: String) {
        _uiState.value = _uiState.value.copy(author = author)
    }

    fun onGenreChange(genre: String) {
        _uiState.value = _uiState.value.copy(genre = genre)
    }

    fun loadBook(bookId: String) {
        viewModelScope.launch {
            bookRepository.fetchById(bookId).collect { book ->
                book?.let {
                    _uiState.value = _uiState.value.copy(
                        title = book.title,
                        author = book.author,
                    )
                }
            }
        }
    }
}