package com.binar.studikasustujuh.datastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.map

class UserManager(context : Context) {
    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "user_prefs")

    companion object{
        val ID = preferencesKey<String>("USER_ID")
        val NAME = preferencesKey<String>("USER_NAME")
        val PASSWORD = preferencesKey<String>("USER_PASSWORD")
        val USERNAME = preferencesKey<String>("USER_USERNAME")

        val UMUR = preferencesKey<String>("USER_UMUR")
        val ADDRESS = preferencesKey<String>("USER_ADDRESS")
        val IMAGE  = preferencesKey<String>("USER_IMAGE")
    }

    suspend fun saveDataUser(id : String, name:String, password : String, username: String, umur: String,  address : String, image: String) {
        dataStore.edit {
            it[ID] = id
            it[PASSWORD] = password
            it[USERNAME] = username
            it[NAME] = name
            it[UMUR] = umur
            it[ADDRESS] = address
            it[IMAGE] = image
        }
    }

    val userUsername : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [USERNAME] ?: ""
    }

}