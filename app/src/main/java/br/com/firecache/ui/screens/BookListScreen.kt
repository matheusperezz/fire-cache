package br.com.firecache.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BookListScreen(
    onAddBookClick: () -> Unit = {}
) {
    Column {
        Text(text = "Book List Screen")
        Button(onClick = { onAddBookClick() }) {
            Text(text = "Adicionar Livro")
        }
    }
}