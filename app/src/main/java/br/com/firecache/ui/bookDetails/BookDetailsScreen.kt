package br.com.firecache.ui.bookDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.firecache.data.models.Book
import br.com.firecache.ui.createBook.BookImage

@Composable
fun BookDetailsScreen(
    bookId: String,
    navHostController: NavHostController
) {
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            Column {
                // BookImage(imageUrl = book.imageUrl)
                Text(text = "Tela de detalhes id: $bookId")
            }
        }
    }
}