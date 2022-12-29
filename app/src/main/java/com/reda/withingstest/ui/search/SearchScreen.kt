package com.reda.withingstest.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.reda.withingstest.ui.components.SearchBar

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToImages: (String) -> Unit
){
    val query by viewModel.searchQuery.collectAsState()
    Column {
        SearchBar(
            modifier = Modifier.padding(16.dp),
            query = query,
            onQueryUpdate = viewModel::onQueryUpdate,
            onCtaClick = { viewModel.onCtaClick(navigateToImages) }
        )
    }
}