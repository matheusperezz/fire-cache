package br.com.firecache

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.firecache.ui.App
import br.com.firecache.ui.theme.FireCacheTheme
import dagger.hilt.android.AndroidEntryPoint

/*
*   TODO: Fazer a listagem de seções
*   TODO: Fazer a listagem de livros por seção
*   TODO: Implementar o formulário de adição de livros
* */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FireCacheTheme {
                App()
            }
        }
    }
}