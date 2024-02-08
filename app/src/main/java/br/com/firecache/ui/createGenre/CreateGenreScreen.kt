package br.com.firecache.ui.createGenre

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.firecache.ui.components.StyledOutlinedTextField

@Composable
fun CreateGenreScreen(
    onSavePopNavigation: () -> Unit = {},
    viewModel: CreateGenreViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        StyledOutlinedTextField(
            value = uiState.name,
            onValueChange = uiState.onNameChange,
            label = "Nome"
        )

        Button(onClick = {
            viewModel.createGenre(uiState.name)
            onSavePopNavigation()
        }) {
            Text(text = "Salvar")
        }
    }

}