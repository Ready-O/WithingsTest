package com.reda.withingstest.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.reda.withingstest.model.Image

@Composable
fun GridImages(
    modifier: Modifier = Modifier,
    list: List<Image>,
    selectItem: (id: Int, isSelected: Boolean) -> Unit
){
    if (list.isNotEmpty()){
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2)
        ){
            itemsIndexed(list){ _,item ->
                val isSelected = remember { mutableStateOf(false) }
                SelectableImageItem(
                    modifier = Modifier.padding(4.dp).sizeIn(minHeight = 100.dp),
                    imageURL = item.largeImageURL,
                    isSelected = isSelected.value
                ) {
                    isSelected.value = !(isSelected.value)
                    selectItem(item.id, isSelected.value)
                }
            }
        }
    }
    else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = "No images found =(")
        }
    }
}