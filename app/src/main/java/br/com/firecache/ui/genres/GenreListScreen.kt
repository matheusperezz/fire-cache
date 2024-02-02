package br.com.firecache.ui.genres

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.firecache.ui.navigation.FirecacheFab

@Composable
fun GenreListScreen(
    onAddGenreClick: () -> Unit = {}
) {
    Scaffold(
        floatingActionButton = {
            FirecacheFab(text = "Adicionar gênero", onClick = onAddGenreClick)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Text(text = "Genres")
        }
    }
}