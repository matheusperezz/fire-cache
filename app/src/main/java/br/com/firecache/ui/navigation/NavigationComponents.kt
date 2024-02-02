package br.com.firecache.ui.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirecacheTopBar(
    title: String
) {
    TopAppBar(
        title = {
            Text(
                text = title, style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 24.sp
                )
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Composable
fun FirecacheFab(
    text: String,
    icon: ImageVector = Icons.Filled.Add,
    onClick: () -> Unit = {}
) {
    ExtendedFloatingActionButton(onClick = { onClick() }) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = text)
    }
}