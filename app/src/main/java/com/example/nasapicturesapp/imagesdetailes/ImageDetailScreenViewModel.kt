package com.example.nasapicturesapp.imagesdetailes

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.nasapicturesapp.data.ImagesProperties

class ImageDetailScreenViewModel @ViewModelInject constructor(): ViewModel() {


     fun extractImageProperties(imageProperties: ImagesProperties?): HashMap<String, String> {
         val Properties = HashMap<String, String>()
         Properties[imageProperties?.title.toString() + '\n' + imageProperties?.date.toString()
                 + '\n' + imageProperties?.explanation.toString()] = imageProperties?.url.toString()
         Properties[""] = imageProperties?.hdurl.toString()
         return Properties
     }


}