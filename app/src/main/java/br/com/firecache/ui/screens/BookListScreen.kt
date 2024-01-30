package br.com.firecache.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.firecache.data.mathBooks
import br.com.firecache.data.models.BookCardModel
import br.com.firecache.ui.components.BookCard
import br.com.firecache.ui.components.BookSection

@Composable
fun BookListScreen(
    onAddBookClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Text(text = "Book List")
        },
        floatingActionButton = {
            Button(onClick = { onAddBookClick() }) {
                Text(text = "Adicionar Livro")
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column {
                Text(text = "Book List Screen")
                BookSection(title = "Matemática", books = mathBooks)
            }
        }
    }
}