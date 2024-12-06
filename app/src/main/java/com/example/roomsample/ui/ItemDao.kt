package com.example.roomsample.ui

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Query("SELECT * FROM Item")
    suspend fun getAllItems(): List<Item>

    @Query("DELETE FROM Item WHERE wordId = :id")
    suspend fun deleteItemById(id: Int)

    @Query("SELECT * FROM Item")
    abstract fun observeAllItems(): Flow<List<Item>>
}