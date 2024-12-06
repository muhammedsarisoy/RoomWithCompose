package com.example.roomsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    val items: LiveData<List<Item>> = repository.observeAllItems().asLiveData()

    fun saveItem(item: Item) {
        viewModelScope.launch {
            repository.insertItem(item)
        }
    }

    fun deleteItemById(id: Int) {
        viewModelScope.launch {
            repository.deleteItemById(id)
        }
    }

    
}