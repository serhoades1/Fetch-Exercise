package com.example.fetchexercise

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    private val repository = ItemRepository()

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    fun fetchItems() {
        viewModelScope.launch {
            _items.value = repository.getFilteredAndSortedItems()
        }
    }
}
