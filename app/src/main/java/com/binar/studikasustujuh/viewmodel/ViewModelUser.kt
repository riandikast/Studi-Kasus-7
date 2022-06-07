package com.binar.studikasustujuh.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.studikasustujuh.api.ApiClient
import com.binar.studikasustujuh.data.GetAllUserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser : ViewModel(){

    var liveDataNewUserItem : MutableLiveData<List<GetAllUserItem>?> = MutableLiveData()
    var liveDataRegis : MutableLiveData<GetAllUserItem?> = MutableLiveData()
    var liveDataUpdate : MutableLiveData<GetAllUserItem> = MutableLiveData()

    fun getLiveUserObserver() : MutableLiveData<List<GetAllUserItem>?> {
        return liveDataNewUserItem
    }

    fun getLiveRegisObserver() : MutableLiveData<GetAllUserItem?> {
        return liveDataRegis
    }

    fun getLiveUpdateObserver() : MutableLiveData<GetAllUserItem> {
        return liveDataUpdate
    }



    fun userApi(){
        ApiClient.instance.getAllUser()
            .enqueue(object : retrofit2.Callback<List<GetAllUserItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserItem>>,
                    getAllItem: Response<List<GetAllUserItem>>
                ) {
                    if (getAllItem.isSuccessful){
                        liveDataNewUserItem.postValue(getAllItem.body())


                    }else{
                        liveDataNewUserItem.postValue(null)

                    }
                }
                override fun onFailure(call: Call<List<GetAllUserItem>>, t: Throwable) {
                    liveDataNewUserItem.postValue(null)
                }
            })
    }


    fun regisUser(username: String, name: String, password: String) {
        ApiClient.instance.registerNew(name,username,password, "", "", "")
            .enqueue(object : Callback<GetAllUserItem> {
                override fun onResponse(
                    call: Call<GetAllUserItem>,
                    response: Response<GetAllUserItem>
                ) {
                    if (response.isSuccessful) {
                        liveDataRegis.postValue(response.body())
                    } else {
                        liveDataRegis.postValue(null)
                    }
                }

                override fun onFailure(call: Call<GetAllUserItem>, t: Throwable) {
                    liveDataRegis.postValue(null)
                }
            })
    }

}