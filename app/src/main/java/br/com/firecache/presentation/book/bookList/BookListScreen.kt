package br.com.firecache.presentation.book.bookList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.firecache.domain.entities.Book
import br.com.firecache.presentation.components.StyledOutlinedTextField
import br.com.firecache.presentation.navigation.FirecacheFab

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookListScreen(
    viewModel: BookListViewModel = hiltViewModel(),
    onBookClick: (String) -> Unit = {},
    onAddBookClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isShowDialog by remember { mutableStateOf(false) }
    var selectedBook by remember { mutableStateOf<Book?>(null) }
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FirecacheFab(text = "Adicionar livro", onClick = onAddBookClick)
        },
    ) {
        when (uiState) {
            is BookListUiState.Loading -> {
                Box(modifier = Modifier.padding(16.dp)) {
                    CircularProgressIndicator()
                }
            }

            is BookListUiState.Success -> {
                val books = (uiState as BookListUiState.Success).books
                BookList(
                    books = books.filter { it.title.contains(searchText, ignoreCase = true) },
                    onBookClick = {
                        onBookClick(it)
                    },
                    searchText = searchText,
                    onSearchTextChange = { searchText = it },
                    onBookLongClick = { book ->
                        selectedBook = book
                        isShowDialog = true
                    }
                )
            }

            is BookListUiState.Empty -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Nenhum livro encontrado!")
                }
            }

            is BookListUiState.Error -> {
                val err = (uiState as BookListUiState.Error).error
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Um erro ocorreu! :( $err")
                }
            }
        }
    }

    selectedBook?.let {
        if (isShowDialog) {
            DeleteBookDialog(
                book = selectedBook!!,
                onDismiss = { isShowDialog = false },
                onDelete = { book ->
                    viewModel.deleteBook(book)
                }
            )
        }
    }

}

@Composable
fun BookList(
    books: List<Book>,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onBookClick: (String) -> Unit = {},
    onBookLongClick: (Book) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StyledOutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            label = "Pesquisar",
            modifier = Modifier.padding(8.dp),
            trailingIcon = {
                Icon(
                    Icons.Default.Search, contentDescription = null
                )
            }
        )

        LazyColumn {
            items(books.filter { it.title.contains(searchText, ignoreCase = true) }) { book ->
                BookCard(
                    book = book,
                    onBookClick = onBookClick,
                    onBookLongClick = onBookLongClick
                )
            }
        }
    }
}

@Composable
fun DeleteBookDialog(
    book: Book,
    onDismiss: () -> Unit,
    onDelete: (Book) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Deletar livro") },
        text = { Text(text = "Tem certeza que deseja deletar o livro ${book.title}?") },
        confirmButton = {
            TextButton(
                onClick = {
                    onDelete(book)
                    onDismiss()
                }
            ) {
                Text(text = "Deletar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancelar")
            }
        }
    )
}