package br.com.firecache

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.firecache.ui.App
import br.com.firecache.ui.theme.FireCacheTheme
import dagger.hilt.android.AndroidEntryPoint

/*
*   TODO: Verifique |UpdateBookViewModel| para saber como receber o id do livro a ser atualizado
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