package br.com.firecache

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import br.com.firecache.presentation.App
import br.com.firecache.presentation.theme.FireCacheTheme
import dagger.hilt.android.AndroidEntryPoint

/*
*   TODO: Verifique |UpdateBookViewModel| para saber como receber o id do livro a ser atualizado
* */

// Data Store preferences
const val PREFERENCE_NAME = "settings"
val USER_KEY = stringPreferencesKey("user")
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

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