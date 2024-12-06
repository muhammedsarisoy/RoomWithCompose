package com.example.roomsample.ui

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class  ItemDataBase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object{
        @Volatile
        private var Instance: ItemDataBase? = null

        fun getDatabase(context: Context): ItemDataBase {
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, ItemDataBase::class.java, "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}