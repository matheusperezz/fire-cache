package br.com.firecache.presentation.book.updateBook

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.firecache.USER_KEY
import br.com.firecache.data.models.Book
import br.com.firecache.data.repositories.BookRepository
import br.com.firecache.dataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UpdateBookUiState(
    val title: String = "",
    val author: String = "",
    val comments: String = "",
)

@HiltViewModel
class UpdateBookViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val bookRepository: BookRepository
) : ViewModel() {

    private val idDataStore = context.dataStore.data.map { preferences ->
        preferences[USER_KEY] ?: ""
    }
    private val _uiState = MutableStateFlow(UpdateBookUiState())
    val uiState get() = _uiState

    init {
        viewModelScope.launch {
            val id = idDataStore.first()
            loadBook(id)
        }
    }

    fun onTitleChange(title: String) {
        _uiState.value = _uiState.value.copy(title = title)
    }

    fun onAuthorChange(author: String) {
        _uiState.value = _uiState.value.copy(author = author)
    }

    fun onCommentsChange(comments: String) {
        _uiState.value = _uiState.value.copy(comments = comments)
    }

    fun updateBook() {
        viewModelScope.launch {
            bookRepository.fetchById(idDataStore.first()).collect { bookCollected ->
                bookCollected?.let { book ->
                    val updatedBook = Book(
                        id = book.id,
                        title = _uiState.value.title,
                        author = _uiState.value.author,
                        genreId = book.genreId,
                        comments = _uiState.value.comments,
                        imageUrl = book.imageUrl,
                        topic = book.topic
                    )
                    bookRepository.update(updatedBook)
                }
            }
        }
    }

    private fun loadBook(bookId: String) {
        viewModelScope.launch {
            bookRepository.fetchById(bookId).collect { book ->
                book?.let {
                    _uiState.value = _uiState.value.copy(
                        title = book.title,
                        author = book.author,
                        comments = book.comments ?: ""
                    )
                }
            }
        }
    }
}