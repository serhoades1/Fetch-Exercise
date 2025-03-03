package com.example.fetchexercise

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getFilteredAndSortedItems(): List<Item> {
        return withContext(Dispatchers.IO) {
            apiService.fetchItems()
                .filter { it.name?.isNotBlank() == true } // Remove null/blank names
                .sortedWith(compareBy({ it.listId }, { it.name })) // Sort by listId, then name
        }
    }
}
