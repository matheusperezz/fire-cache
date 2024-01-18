package br.com.firecache.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import br.com.firecache.data.mathBooks
import br.com.firecache.data.models.BookCardModel
import br.com.firecache.ui.components.BookCard
import br.com.firecache.ui.components.BookSection

@Composable
fun BookListScreen(
    onAddBookClick: () -> Unit = {}
) {
    Column {
        Text(text = "Book List Screen")
        Button(onClick = { onAddBookClick() }) {
            Text(text = "Adicionar Livro")
        }
        BookSection(title = "Matemática", books = mathBooks)
    }
}