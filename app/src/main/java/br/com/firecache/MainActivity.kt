package br.com.firecache

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.firecache.ui.App
import br.com.firecache.ui.theme.FireCacheTheme
import dagger.hilt.android.AndroidEntryPoint

/*
    TODO: Melhorar a tela de listagem de livros, pois removi a parte de gêneros
    TODO: Implementar a tela de detalhes do livro
*   TODO: Implementar a imagem no formulário de adição de livros, de preferencia
*    um botão para carregar a imagem em algum container
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