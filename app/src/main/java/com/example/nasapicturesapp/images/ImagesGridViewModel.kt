package com.example.nasapicturesapp.images

import android.content.Context
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasapicturesapp.data.JsonImages
import com.example.nasapicturesapp.utils.JsonUtil
import com.example.nasapicturesapp.utils.JsonUtil.getAssetImages
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch


class ImagesGridViewModel @ViewModelInject constructor(@ApplicationContext var context: Context): ViewModel() {

    private val _jsonImagesResponse = MutableLiveData<List<JsonImages>>()
    val jsonImagesResponse: LiveData<List<JsonImages>>
        get() = _jsonImagesResponse

   init {
       getAllImagesFromFile()
   }

    private fun getAllImagesFromFile() {
        viewModelScope.launch {
            _jsonImagesResponse.value = getAssetImages(context)
        }
    }


}