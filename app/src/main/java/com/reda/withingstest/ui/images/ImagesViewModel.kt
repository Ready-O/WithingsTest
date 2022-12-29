package com.reda.withingstest.ui.images

import androidx.lifecycle.ViewModel
import com.reda.withingstest.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val imageRepository: ImageRepository
): ViewModel() {
    
}