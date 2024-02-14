package br.com.firecache.ui.genre.genreList

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.firecache.ui.navigation.FirecacheFab

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GenreListScreen(
    onAddGenreClick: () -> Unit = {},
    viewModel: GenreListViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FirecacheFab(text = "Adicionar gênero", onClick = onAddGenreClick)
        }
    ) {
        when (uiState) {
            is GenreListUiState.Loading -> {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text("Carregando...")
                }
            }

            is GenreListUiState.Success -> {
                val genres = (uiState as GenreListUiState.Success).genres
                LazyColumn {
                    items(genres) { genre ->
                        Text(
                            text = genre.name,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .clickable { })
                    }
                }
            }

            is GenreListUiState.Error -> {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text("Erro ao carregar os gêneros")
                }
            }

            is GenreListUiState.Empty -> {
                Box(modifier = Modifier.padding(16.dp)) {
                    Text("Nenhum gênero encontrado")
                }
            }
        }
    }
}