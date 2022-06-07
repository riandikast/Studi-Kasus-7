package com.binar.studikasustujuh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.studikasustujuh.NewsRepository
import com.binar.studikasustujuh.data.ResponseNewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val api: NewsRepository): ViewModel() {
    private val newsState = MutableStateFlow(emptyList<ResponseNewsItem>())
    val dataState : StateFlow<List<ResponseNewsItem>>
        get() = newsState

    init {
        viewModelScope.launch {
            val news = api.getNews()
            newsState.value = news
        }
    }
}