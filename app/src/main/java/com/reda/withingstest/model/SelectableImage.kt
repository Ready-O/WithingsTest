package com.reda.withingstest.model

data class SelectableImage(
    val id: Int,
    val imageUrl: String,
    val isSelected: Boolean
)

fun Image.toSelectable() =
    SelectableImage(
        id = id,
        imageUrl = largeImageURL,
        isSelected = false
    )
