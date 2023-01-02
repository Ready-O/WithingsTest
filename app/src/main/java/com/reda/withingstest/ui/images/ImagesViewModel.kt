package com.reda.withingstest.ui.images

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reda.withingstest.model.Image
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

    private val selectedList = mutableListOf<Image>()

    init {
        viewModelScope.launch {
            if(searchQuery != null){
                imageRepository.fetchImages(searchQuery).collectLatest {
                    it.onSuccess { list ->
                        val selectableList = mutableListOf<Image>()
                        list.forEach { image ->
                            selectableList.add(image)
                        }
                        _imagesState.value = ListImagesState.ListImages(selectableList,selectedList.size)
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

    fun onSelectImage(imageId: Int, isSelected: Boolean){
        viewModelScope.launch {
            val list = (imagesState.value as ListImagesState.ListImages).list
            val element = list.first { it.id == imageId }
            if (isSelected){
                selectedList.add(element)
            }
            else {
                selectedList.remove(element)
            }
            _imagesState.value = ListImagesState.ListImages(list,selectedList.size)
        }
    }

    fun onCtaClicked(){
        viewModelScope.launch{
            _imagesState.value = ListImagesState.SelectedImages(selectedList)
        }
    }

}