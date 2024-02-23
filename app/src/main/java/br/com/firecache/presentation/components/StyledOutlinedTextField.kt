package br.com.firecache.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StyledOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        shape = RoundedCornerShape(25.dp),
        modifier = modifier.fillMaxWidth(),
        trailingIcon = { trailingIcon?.invoke() },
    )
}

@Preview(showBackground = true)
@Composable
private fun StyledOutlinedTextFieldPreview() {
    StyledOutlinedTextField(
        value = "Value",
        onValueChange = {},
        label = "Text Field Label"
    )
}