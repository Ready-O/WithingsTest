package com.reda.withingstest.ui.images

import com.reda.withingstest.model.Image

interface ListImagesState {

    object Loading: ListImagesState

    data class ListImages(val list: MutableList<Image>, val numberSelected: Int): ListImagesState

    data class SelectedImages(val list: List<Image>): ListImagesState

    data class Error(val throwable: Throwable) : ListImagesState
}