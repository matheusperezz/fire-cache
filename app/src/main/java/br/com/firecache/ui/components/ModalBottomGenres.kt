package br.com.firecache.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.firecache.data.models.Genre

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomGenres(
    genreList: List<Genre>,
    onGenreSelect: (Genre) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    ModalBottomSheet(onDismissRequest = { onDismiss() }) {
        LazyColumn(
            modifier = Modifier.padding(8.dp)
        ) {
            items(genreList) { genre ->
                Column {
                    Divider()
                    ModalBottomGenreTile(genre = genre, onGenreSelect = {
                        onGenreSelect(genre)
                        onDismiss()
                    })
                }
            }

            item{
                Spacer(modifier = Modifier.padding(vertical = 26.dp))
            }
        }
    }
}

@Composable
private fun ModalBottomGenreTile(genre: Genre, onGenreSelect: () -> Unit = {}) {
    Row(modifier = Modifier
        .padding(8.dp)
        .clickable {
            onGenreSelect()
        }) {
        Text(text = genre.name)
    }
}