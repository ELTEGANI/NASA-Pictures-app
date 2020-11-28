package com.example.nasapicturesapp.imagesdetailes

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.nasapicturesapp.data.ImagesProperties

class ImageDetailScreenViewModel @ViewModelInject constructor(): ViewModel() {


     fun extractImageProperties(imageProperties: ImagesProperties?): HashMap<String, String> {
         val properties = HashMap<String, String>()
         properties[imageProperties?.title.toString() + '\n' + imageProperties?.date.toString()
                 + '\n' + imageProperties?.explanation.toString()] = imageProperties?.url.toString()
         properties[""] = imageProperties?.hdurl.toString()
         return properties
     }


}