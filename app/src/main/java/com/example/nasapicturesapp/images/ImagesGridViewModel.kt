package com.example.nasapicturesapp.images

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasapicturesapp.data.ImagesProperties
import com.example.nasapicturesapp.repository.ImagesRepository
import kotlinx.coroutines.launch


class ImagesGridViewModel @ViewModelInject constructor(var imagesRepository: ImagesRepository): ViewModel() {

    //TODO lates images
    private val _jsonImages = MutableLiveData<List<ImagesProperties>>()
    val jsonImagesProperties: LiveData<List<ImagesProperties>>
        get() = _jsonImages

    private val _navigateToSelectedProperty = MutableLiveData<ImagesProperties>()
    val navigateToSelectedProperty: LiveData<ImagesProperties>
        get() = _navigateToSelectedProperty

   init {
       readAllImagesFromJsonFile()
   }

    private fun readAllImagesFromJsonFile() {
        viewModelScope.launch {
            _jsonImages.value = imagesRepository.retrieveAllJsonImages()
        }
    }

    fun displayPropertyDetails(imagesProperties: ImagesProperties) {
        _navigateToSelectedProperty.value = imagesProperties
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }


}