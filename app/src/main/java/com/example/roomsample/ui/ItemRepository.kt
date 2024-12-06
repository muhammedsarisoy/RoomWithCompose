package com.example.roomsample.ui

import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {

    suspend fun insertItem(item: Item) {
        itemDao.insert(item)
    }

    suspend fun getAllItems(): List<Item> {
        return itemDao.getAllItems()
    }

    suspend fun deleteItemById(id: Int) {
        itemDao.deleteItemById(id)
    }

    fun observeAllItems(): Flow<List<Item>> {
        return itemDao.observeAllItems()
    }
}