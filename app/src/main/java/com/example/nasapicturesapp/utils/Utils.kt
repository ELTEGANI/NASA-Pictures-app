package com.example.nasapicturesapp.utils

import android.content.Context
import com.example.nasapicturesapp.data.JsonImages
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object JsonUtil {
    fun getAssetImages(context: Context): List<JsonImages>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listType = Types.newParameterizedType(List::class.java,JsonImages::class.java)
        val adapter: JsonAdapter<List<JsonImages>> = moshi.adapter(listType)
        val jsonFile = "data.json"
        val imagesJson = context.assets.open(jsonFile).bufferedReader().use{ it.readText()}
        return adapter.fromJson(imagesJson)
    }
}