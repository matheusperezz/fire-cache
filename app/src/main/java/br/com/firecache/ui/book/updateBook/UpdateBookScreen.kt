package br.com.firecache.ui.book.updateBook

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.firecache.ui.components.StyledOutlinedTextField

@Composable
fun UpdateBookScreen(
    viewModel: UpdateBookViewModel = hiltViewModel(),
    bookId: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {

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
    }
}