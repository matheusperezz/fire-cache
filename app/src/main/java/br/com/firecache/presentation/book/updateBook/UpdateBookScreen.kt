package br.com.firecache.presentation.book.updateBook

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import br.com.firecache.presentation.components.StyledOutlinedTextField

@Composable
fun UpdateBookScreen(
    viewModel: UpdateBookViewModel = hiltViewModel(),
    navHostController: NavHostController,
    bookId: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        StyledOutlinedTextField(
            value = uiState.title,
            onValueChange = {
                viewModel.onTitleChange(it)
                Log.i("UPDATEBOOK", "UpdateBookScreen: $it")
            },
            label = "Título"
        )

        StyledOutlinedTextField(
            value = uiState.author,
            onValueChange = viewModel::onAuthorChange,
            label = "Autor"
        )

        StyledOutlinedTextField(
            value = uiState.comments,
            onValueChange = viewModel::onCommentsChange,
            label = "Comentários"
        )

        Button(onClick = {
            viewModel.updateBook()
            navHostController.popBackStack()
        }) {
            Text(text = "Atualizar")
        }

    }
}