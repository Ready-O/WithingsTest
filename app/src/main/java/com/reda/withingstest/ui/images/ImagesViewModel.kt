package com.reda.withingstest.ui.images

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reda.withingstest.model.SelectableImage
import com.reda.withingstest.model.toSelectable
import com.reda.withingstest.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val imageRepository: ImageRepository
): ViewModel() {

    private val searchQuery: String? = savedStateHandle["searchQuery"]

    private val _imagesState = MutableStateFlow<ListImagesState>(ListImagesState.Loading)
    val imagesState: StateFlow<ListImagesState> = _imagesState.asStateFlow()

    val selectedList = mutableListOf<SelectableImage>()

    init {
        viewModelScope.launch {
            if(searchQuery != null){
                imageRepository.fetchImages(searchQuery).collectLatest {
                    it.onSuccess { list ->
                        val selectableList = mutableListOf<SelectableImage>()
                        list.forEach { image ->
                            selectableList.add(image.toSelectable())
                        }
                        _imagesState.value = ListImagesState.ListImages(selectableList)
                    }
                        .onFailure { throwable ->
                            _imagesState.value = ListImagesState.Error(throwable)
                        }
                }
            }
            else {
                _imagesState.value = ListImagesState.Error(Throwable(message = "Error"))
            }
        }
    }

    fun onSelectImage(index: Int,imageId: Int){
        viewModelScope.launch {
            val list = (imagesState.value as ListImagesState.ListImages).list
            val element = list.first { it.id == imageId }
            val updatedElement = element.copy(isSelected = !(element.isSelected))
            if (updatedElement.isSelected){
                selectedList.add(updatedElement)
            }
            else {
                selectedList.remove(updatedElement)
            }
            list.set(index = index, element = updatedElement)
            _imagesState.value = ListImagesState.ListImages(list)
        }
    }

}