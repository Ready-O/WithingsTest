package com.reda.withingstest.ui.images


import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.reda.withingstest.model.SelectableImage
import com.reda.withingstest.ui.components.ImageItem
import com.reda.withingstest.ui.components.SelectableImageItem
import com.reda.withingstest.ui.components.LoadingBox

@OptIn(ExperimentalMaterial3Api::class)
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
            Column(
                modifier = Modifier.padding(8.dp)
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
                            onClick = viewModel::onCtaClicked
                        ) {
                            Text("Confirm")
                        }
                    }
                )
                ListImages(
                    list = imagesState.list,
                    selectItem = viewModel::onSelectImage
                )
            }
        }
        is ListImagesState.SelectedImages -> {
            val imagesState = state as ListImagesState.SelectedImages
            SelectedImages(imagesState.list)
        }
        is ListImagesState.Error -> {
            val listState = state as ListImagesState.Error
            Text(text = " : ${listState.throwable}")
        }
    }
}

@Composable
private fun ListImages(
    list: List<SelectableImage>,
    selectItem: (index: Int, id: Int) -> Unit
){
    if (list.isNotEmpty()){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ){
            itemsIndexed(list){ index,item ->
                SelectableImageItem(
                    imageURL = item.imageUrl,
                    isSelected = item.isSelected,
                    onClick = { selectItem(index, item.id)}
                )
            }
        }
    }
    else {
        Text(text = "No images found =(")
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SelectedImages(
    list: List<SelectableImage>,
){
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut()
    ) {
        LazyColumn {
            itemsIndexed(list) { index, item ->
                ImageItem(
                    imageURL = item.imageUrl,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) }
                            )
                        )
                )
            }
        }
    }
}
