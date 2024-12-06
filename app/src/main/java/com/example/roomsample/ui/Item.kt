package com.example.roomsample.ui

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val wordId: Int = 0,
    @ColumnInfo(name = "text1")
    val text1: String,
    @ColumnInfo(name = "text2")
    val text2: String
)
