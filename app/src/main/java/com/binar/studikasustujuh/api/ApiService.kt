package com.binar.studikasustujuh.api

import com.binar.studikasustujuh.data.GetAllUserItem
import com.binar.studikasustujuh.data.ResponseNewsItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("news")
    suspend fun getAllNews():List<ResponseNewsItem>

    @POST("user")
    @FormUrlEncoded
    suspend fun registerNew(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("address") address: String,
        @Field("image") image: String,
        @Field("umur") umur: String,
    ): Call<GetAllUserItem>

    @GET("user")
    suspend fun getAllUser(): List<GetAllUserItem>
}