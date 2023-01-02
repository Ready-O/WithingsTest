package com.reda.withingstest.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagesTopBar(
    isCtaEnabled: Boolean,
    navigateUp: () -> Unit,
    onCtaClicked: () -> Unit
){
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        title = { Text("Select Images") },
        actions = {
            Button(
                modifier = Modifier.padding(horizontal = 12.dp),
                enabled = isCtaEnabled,
                onClick = onCtaClicked
            ) {
                Text("Confirm")
            }
        }
    )
}