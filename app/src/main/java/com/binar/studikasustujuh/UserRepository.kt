package com.binar.studikasustujuh

import androidx.lifecycle.MutableLiveData
import com.binar.studikasustujuh.api.ApiService
import com.binar.studikasustujuh.data.GetAllUserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userapi : ApiService) {
    suspend fun getUser(): List<GetAllUserItem>{
        return userapi.getAllUser()
    }

    fun regisUser(username: String, password: String, name : String, liveData: MutableLiveData<GetAllUserItem>) {
        val apiClient : Call <GetAllUserItem> = userapi.registerNew(username,name, password, "", "", "")
        apiClient.enqueue(object : Callback<GetAllUserItem> {
                override fun onResponse(
                    call: Call<GetAllUserItem>,
                    response: Response<GetAllUserItem>
                ) {
                    if (response.isSuccessful) {
                        liveData.postValue(response.body())
                    } else {
                        liveData.postValue(null)
                    }
                }

                override fun onFailure(call: Call<GetAllUserItem>, t: Throwable) {
                    liveData.postValue(null)
                }
            })
    }

}