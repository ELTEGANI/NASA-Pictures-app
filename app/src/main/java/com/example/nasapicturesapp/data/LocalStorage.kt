package com.example.nasapicturesapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.*
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalStorage @Inject constructor(@ApplicationContext var context: Context) {

    companion object {
        private val UI_MODE_KEY = preferencesKey<Boolean>("ui_mode")
    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(
            name = "ui_mode_preference"
    )

    suspend fun saveToDataStore(isNightMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[UI_MODE_KEY] = isNightMode
        }
    }

    val uiMode: Flow<Boolean> = dataStore.data
            .map { preferences ->
                val uiMode = preferences[UI_MODE_KEY] ?: false
                uiMode
            }
}