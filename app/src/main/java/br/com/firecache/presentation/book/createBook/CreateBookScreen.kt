package br.com.firecache.presentation.book.createBook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.firecache.domain.entities.Book
import br.com.firecache.presentation.components.ModalBottomGenres
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.shared_ui.components.RowTextWithIcon
import com.example.shared_ui.components.StyledOutlinedTextField

@Composable
fun CreateBookScreen(
    onSaveClick: () -> Unit = {},
    viewModel: CreateBookViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
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

        BookImage(imageUrl = uiState.imageUrl, modifier = Modifier.size(200.dp))

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

        StyledOutlinedTextField(
            value = uiState.comments,
            onValueChange = uiState.onCommentsChange,
            label = "Comentários"
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
            saveBook(uiState, viewModel)
            onSaveClick()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Adicionar livro")
        }
    }
}

private fun saveBook(
    uiState: CreateBookUiState,
    viewModel: CreateBookViewModel
) {
    uiState.selectedGenre?.let { notNullGenre ->
        viewModel.createBook(
            Book(
                title = uiState.title,
                author = uiState.author,
                imageUrl = uiState.imageUrl,
                topic = uiState.topic,
                genreId = notNullGenre.id,
                comments = uiState.comments
            )
        )
    }
}

@Composable
fun BookImage(imageUrl: String, modifier: Modifier = Modifier) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build(),
        contentDescription = "Book image",
        modifier = modifier,
        contentScale = ContentScale.Fit,
        loading = {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp)
            )
        },
        error = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Imagem não encontrada.")
            }
        }
    )
}