package br.com.firecache.ui.addbook

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddBookScreen() {
    Scaffold {
        Box(modifier = Modifier.padding(it)){
            Column {
                Text(text = "Add Book Screen")
            }
        }
    }
}