package br.com.firecache.ui.book.bookDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import br.com.firecache.data.models.Book

@Composable
fun BookDetailsScreen(
    onEditClick: (String) -> Unit = {},
    viewModel: BookDetailsViewModel = hiltViewModel(),
    navHostController: NavHostController,
    bookId: String,
) {
    var isShowingFab by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.fetchBook(bookId)

    Scaffold(
        floatingActionButton = {
            if (isShowingFab) {
                FloatingActionButton(onClick = {
                    val book = (uiState as BookDetailsUiState.Success).book
                    onEditClick(book.id)
                }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            when (uiState) {
                is BookDetailsUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is BookDetailsUiState.Success -> {
                    val book = (uiState as BookDetailsUiState.Success).book
                    isShowingFab = true
                    BookInfo(book = book)
                }

                is BookDetailsUiState.NotFound -> {
                    Text(text = "Livro não encontrado")
                }

                is BookDetailsUiState.Error -> {
                    Text(text = "Erro ao carregar livro")
                }
            }
        }
    }
}

@Composable
fun BookInfo(
    book: Book
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = book.title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = book.author,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        if (book.comments != null) {
            Text(
                text = book.comments,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = "Sem comentários",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}