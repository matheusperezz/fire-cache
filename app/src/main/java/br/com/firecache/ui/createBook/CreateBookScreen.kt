package br.com.firecache.ui.createBook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBookScreen(
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
            if (uiState.selectedGenre == null){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Selecione um gênero")
                    IconButton(onClick = { viewModel.toggleBottomSheet() }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                    }
                }
            } else {
                Row {
                    Text(text = "Gênero selecionado: ${uiState.selectedGenre!!.name}")
                    IconButton(onClick = { viewModel.toggleBottomSheet() }) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
                    }
                }
            }

            if (uiState.isShowGenreBottomSheet) {
                ModalBottomSheet(onDismissRequest = { viewModel.toggleBottomSheet() }) {
                    LazyColumn {
                        items(uiState.genreList){ genre ->
                            Text(text = genre.name)
                        }
                    }
                }
            }

            Button(onClick = {
                // TODO: Add book with the corresponding data
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Adicionar livro")
            }
        }
    }
}

@Composable
fun StyledOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        shape = RoundedCornerShape(25.dp),
        modifier = modifier.fillMaxWidth()
    )
}