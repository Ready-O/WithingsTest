package com.reda.withingstest.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.reda.withingstest.R

@Composable
fun ImageItem(
    imageURL: String,
    isSelected: Boolean,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier.clickable { onClick() }
    ){
        AsyncImage(
            modifier = Modifier.padding(4.dp).sizeIn(minHeight = 100.dp),
            model = imageURL,
            fallback = painterResource(id = R.drawable.ic_baseline_photo_24),
            placeholder = painterResource(id = R.drawable.ic_baseline_photo_24),
            contentDescription = null
        )
        if (isSelected){
            Icon(
                modifier = Modifier.align(Alignment.TopEnd)
                    .padding(top = 4.dp,end = 4.dp),
                imageVector = Icons.Filled.Check,
                tint = Color.Red,
                contentDescription = null,
            )
        }
    }
}