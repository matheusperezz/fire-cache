package br.com.firecache.ui.createBook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.firecache.data.models.BookCardModel
import br.com.firecache.ui.components.ModalBottomGenres
import br.com.firecache.ui.components.RowTextWithIcon
import br.com.firecache.ui.components.StyledOutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBookScreen(
    onSaveClick: () -> Unit = {},
    viewModel: CreateBookViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            StyledOutlinedTextField(
                value = uiState.title,
                onValueChange = uiState.onTitleChange,
                label = "Título"
            )
            StyledOutlinedTextField(
                value = uiState.author,
                onValueChange = uiState.onAuthorChange,
                label = "Autor"
            )
            StyledOutlinedTextField(
                value = uiState.imageUrl,
                onValueChange = uiState.onImageUrlChange,
                label = "URL da imagem"
            )
            StyledOutlinedTextField(
                value = uiState.topic,
                onValueChange = uiState.onTopicChange,
                label = "Tópico"
            )
            if (uiState.selectedGenre == null) {
                RowTextWithIcon(text = "Selecione um gênero", icon = Icons.Filled.Add) {
                    viewModel.toggleBottomSheet()
                }
            } else {
                RowTextWithIcon(
                    text = "Gênero selecionado: ${uiState.selectedGenre!!.name}",
                    icon = Icons.Filled.Edit
                ) {
                    viewModel.toggleBottomSheet()
                }
            }

            if (uiState.isShowGenreBottomSheet) {
                ModalBottomGenres(
                    genreList = uiState.genreList,
                    onDismiss = {
                        viewModel.toggleBottomSheet()
                    },
                    onGenreSelect = { selectedGenre ->
                        viewModel.selectGenre(selectedGenre)
                    },
                )
            }

            Button(onClick = {
                uiState.selectedGenre?.let { notNullGenre ->
                    viewModel.createBook(
                        BookCardModel(
                            title = uiState.title,
                            author = uiState.author,
                            imageUrl = uiState.imageUrl,
                            topic = uiState.topic,
                            genreId = notNullGenre.id
                        )
                    )
                }
                onSaveClick()
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Adicionar livro")
            }
        }
    }
}

