package com.reda.withingstest.ui.images


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.reda.withingstest.ui.components.*

@Composable
fun ImagesScreen(
    viewModel: ImagesViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
){
    val state by viewModel.imagesState.collectAsState()
    when(state){
        is ListImagesState.Loading -> LoadingBox()
        is ListImagesState.ListImages -> {
            val imagesState = state as ListImagesState.ListImages
            Column(){
                ImagesTopBar(
                    isCtaEnabled = imagesState.numberSelected >= 2,
                    navigateUp = navigateUp,
                    onCtaClicked = viewModel::onCtaClicked
                )
                GridImages(
                    modifier = Modifier.padding(8.dp),
                    list = imagesState.list,
                    selectItem = viewModel::onSelectImage
                )
            }
        }
        is ListImagesState.SelectedImages -> {
            val imagesState = state as ListImagesState.SelectedImages
            AnimatedImagesScreen(imagesState.list)
        }
        is ListImagesState.Error -> {
            val listState = state as ListImagesState.Error
            Text(text = "Error : ${listState.throwable}")
        }
    }
}

