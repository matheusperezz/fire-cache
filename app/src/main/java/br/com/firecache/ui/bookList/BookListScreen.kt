package br.com.firecache.ui.bookList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.firecache.data.mathBooks
import br.com.firecache.ui.bookList.BookListTopBar
import br.com.firecache.ui.bookList.BookSection

@Composable
fun BookListScreen(
    onAddBookClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            BookListTopBar(title = "Super exatas")
        },
        floatingActionButton = {
            BookListFloatingActionButton(text = "Adicionar livro", onClick = onAddBookClick)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column {
                BookSection(title = "Matemática", books = mathBooks)
            }
        }
    }
}