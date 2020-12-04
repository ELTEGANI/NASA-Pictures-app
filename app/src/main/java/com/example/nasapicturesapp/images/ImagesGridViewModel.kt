package com.example.nasapicturesapp.images

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasapicturesapp.data.ImagesProperties
import com.example.nasapicturesapp.data.LocalStorage
import com.example.nasapicturesapp.repository.ImagesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class ImagesGridViewModel @ViewModelInject constructor(var imagesRepository: ImagesRepository,
                                                       var localStorage: LocalStorage): ViewModel() {

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

    // Save to DataStore
    fun saveToDataStore(isNightMode: Boolean) {
        viewModelScope.launch(IO) {
            localStorage.saveToDataStore(isNightMode)
        }
    }

    // Get From DataStore
    val readDataStore = localStorage.uiMode
}