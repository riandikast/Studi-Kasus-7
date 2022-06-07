package com.binar.studikasustujuh

import com.binar.studikasustujuh.api.ApiService
import com.binar.studikasustujuh.data.ResponseNewsItem
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsapi : ApiService) {
    suspend fun getNews(): List<ResponseNewsItem>{
        return newsapi.getAllNews()
    }
}