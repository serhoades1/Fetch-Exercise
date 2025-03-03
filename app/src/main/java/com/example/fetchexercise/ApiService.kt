package com.example.fetchexercise

import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun fetchItems(): List<Item>
}
