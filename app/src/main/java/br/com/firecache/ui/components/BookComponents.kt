package br.com.firecache.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.firecache.data.mathBooks
import br.com.firecache.data.models.BookCardModel
import coil.compose.AsyncImage

@Composable
fun BookSection(
    title: String,
    books: List<BookCardModel>,
    onBookClick: (BookCardModel) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(text = title)

            Button(onClick = { }) {
                Text(text = "Ver mais")
            }
        }

        LazyRow {

            item {
                Spacer(modifier = Modifier.size(12.dp))
            }

            items(books) {
                BookCard(book = it, onBookClick = { onBookClick(it) })
                Spacer(modifier = Modifier.size(12.dp))
            }

        }
    }
}

@Composable
fun BookCard(
    book: BookCardModel,
    onBookClick: (BookCardModel) -> Unit = {}
) {
    Card(
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .width(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .height(366.dp)
            .clickable {
                onBookClick(book)
            },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = book.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            ) {

                BookText(
                    text = book.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )

                BookText(
                    text = "${book.genre} - ${book.topic}",
                    fontWeight = FontWeight.Light,
                    fontStyle =
                    FontStyle.Italic
                )

                BookText(
                    text = book.author,
                )
            }
        }

    }
}

@Composable
fun BookText(
    text: String,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLines: Int = 1,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = TextStyle(
            fontStyle = fontStyle,
            fontWeight = fontWeight
        ),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun BookCardPreview() {
    val bookModel = BookCardModel(
        id = "1",
        title = "Matemática Elementar 1 - Conjuntos e funções com exercícios",
        author = "Eizzel",
        imageUrl = "https://m.media-amazon.com/images/I/71KGGRF6WTL._SL1403_.jpg",
        topic = "Funções",
        genre = "Matemática"
    )
    BookCard(book = bookModel)
}

@Preview(showBackground = true)
@Composable
private fun BookSectionPreview() {
    BookSection(title = "Matemática", books = mathBooks)
}