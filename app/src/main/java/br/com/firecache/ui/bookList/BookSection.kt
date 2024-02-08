package br.com.firecache.ui.bookList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.sp
import br.com.firecache.data.mathBooks
import br.com.firecache.data.models.Book
import coil.compose.AsyncImage

@Composable
fun BookSection(
    title: String,
    books: List<Book>,
    onBookClick: (Book) -> Unit = {},
    onBookLongClick: (Book) -> Unit = {},
    onArrowClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(
                text = title,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier.padding(start = 8.dp)
            )

            IconButton(onClick = { onArrowClick() }) {
                Icon(Icons.Filled.ArrowForward, null)
            }
        }

        LazyRow {

            item {
                Spacer(modifier = Modifier.size(12.dp))
            }

            items(books) {
                BookCard(
                    book = it,
                    onBookClick = { book -> onBookClick(book) },
                    onBookLongClick = { book -> onBookLongClick(book) })
                Spacer(modifier = Modifier.size(12.dp))
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookCard(
    book: Book,
    onBookClick: (Book) -> Unit = {},
    onBookLongClick: (Book) -> Unit = {}
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
            .combinedClickable(
                onClick = { onBookClick(book) },
                onLongClick = { onBookLongClick(book) }
            ),
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
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {

                BookText(
                    text = book.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )

                BookText(
                    text = book.topic,
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
    modifier: Modifier = Modifier,
    text: String,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLines: Int = 1,
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
    val bookModel = Book(
        id = "1",
        title = "Matemática Elementar 1 - Conjuntos e funções com exercícios",
        author = "Eizzel",
        imageUrl = "https://m.media-amazon.com/images/I/71KGGRF6WTL._SL1403_.jpg",
        topic = "Funções",
        genreId = "1"
    )
    BookCard(book = bookModel)
}

@Preview(showBackground = true)
@Composable
private fun BookSectionPreview() {
    BookSection(title = "Matemática", books = mathBooks)
}