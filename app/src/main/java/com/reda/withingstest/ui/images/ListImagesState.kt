package com.reda.withingstest.ui.images

import com.reda.withingstest.model.Image
import com.reda.withingstest.model.SelectableImage

interface ListImagesState {

    object Loading: ListImagesState

    data class ListImages(val list: MutableList<SelectableImage>): ListImagesState

    data class SelectedImages(val list: List<SelectableImage>): ListImagesState

    data class Error(val throwable: Throwable) : ListImagesState
}