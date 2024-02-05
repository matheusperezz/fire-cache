package br.com.firecache.ui.bookList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.firecache.data.mathBooks
import br.com.firecache.data.models.BookCardModel
import br.com.firecache.ui.navigation.FirecacheFab
import br.com.firecache.ui.navigation.FirecacheTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookListScreen(
    viewModel: BookListViewModel = hiltViewModel(),
    onAddBookClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FirecacheFab(text = "Adicionar livro", onClick = onAddBookClick)
        },
    ) {
        when (uiState){
            is BookListUiState.Loading -> {
                Box(modifier = Modifier.padding(16.dp)) {
                    CircularProgressIndicator()
                }
            }
            is BookListUiState.Success -> {
                val books = (uiState as BookListUiState.Success).books
                BookList(books = books)
            }
            is BookListUiState.Empty -> {
                Column {
                    Text(text = "Nenhum livro encontrado!")
                }
            }
            is BookListUiState.Error -> {
                Column {
                    Text(text = "Um erro ocorreu! :(")
                }
            }
        }
    }


}

@Composable
fun BookList(
    books: List<BookCardModel>,
) {
    Column {
        BookSection(title = "Matemática", books = books)
    }
}