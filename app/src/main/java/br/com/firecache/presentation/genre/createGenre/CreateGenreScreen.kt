package br.com.firecache.presentation.genre.createGenre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.firecache.presentation.components.StyledOutlinedTextField

@Composable
fun CreateGenreScreen(
    onSavePopNavigation: () -> Unit = {},
    viewModel: CreateGenreViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.spacedBy(
            8.dp,
            Alignment.CenterVertically
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        StyledOutlinedTextField(
            value = uiState.name,
            onValueChange = uiState.onNameChange,
            label = "Nome"
        )

        Button(
            onClick = {
                viewModel.createGenre(uiState.name)
                onSavePopNavigation()
            }, modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Salvar")
        }
    }

}