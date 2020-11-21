package com.example.vendasmae.banco.itens

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {

        val nomeDoBanco = "item.db"

        lateinit var db: ItemDatabase

        fun getInstance(context: Context): ItemDatabase {
            if(!::db.isInitialized){
                db = Room.databaseBuilder(
                    context,
                    ItemDatabase::class.java,
                    nomeDoBanco
                ).build()
            }

            return db
        }
    }

}