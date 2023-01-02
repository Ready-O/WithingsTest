package com.reda.withingstest.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryUpdate: (String) -> Unit,
    onCtaClick: () -> Unit
){

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Row(
        modifier = modifier
    ){
        OutlinedTextField(
            modifier = Modifier.weight(1f).focusRequester(focusRequester),
            value = query,
            placeholder = { Text("Search images")},
            onValueChange = onQueryUpdate,
            maxLines = 1,
            singleLine = true,
        )
        Button(
            modifier = Modifier.padding(horizontal = 8.dp),
            enabled = query.isNotEmpty(),
            onClick = {
                keyboardController?.hide()
                onCtaClick()
            }
        ) {
            Text("Search")
        }
    }

}