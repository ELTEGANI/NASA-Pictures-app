package com.example.nasapicturesapp.repository

import android.content.Context
import com.example.nasapicturesapp.data.ImagesProperties
import com.example.nasapicturesapp.data.LocalStorage
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject



class ImagesRepository @Inject constructor(@ApplicationContext var context: Context) {
    fun retrieveAllJsonImages():List<ImagesProperties>? {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val listType = Types.newParameterizedType(List::class.java, ImagesProperties::class.java)
            val adapter: JsonAdapter<List<ImagesProperties>> = moshi.adapter(listType)
            val jsonFile = "data.json"
            val imagesJson = context.assets.open(jsonFile).bufferedReader().use{ it.readText()}
            return adapter.fromJson(imagesJson)
    }

}