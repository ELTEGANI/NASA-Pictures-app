package com.example.nasapicturesapp.images

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasapicturesapp.data.ImagesProperties
import com.example.nasapicturesapp.utils.JsonUtil.getAssetImages
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch


class ImagesGridViewModel @ViewModelInject constructor(@ApplicationContext var context: Context): ViewModel() {

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
            _jsonImages.value = getAssetImages(context)
        }
    }

    fun displayPropertyDetails(imagesProperties: ImagesProperties) {
        _navigateToSelectedProperty.value = imagesProperties
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }


}