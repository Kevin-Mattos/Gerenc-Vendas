package com.example.vendasmae.banco.vendedoras

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Vendedora::class], version = 1)
abstract class VendedoraDatabase: RoomDatabase() {
    abstract fun vendedoraDao(): VendedoraDao

    companion object {

        val nomeDoBanco = "vendedora.db"

        lateinit var db: VendedoraDatabase

        fun getInstance(context: Context): VendedoraDatabase {
            if(!::db.isInitialized){
                db = Room.databaseBuilder(
                    context,
                    VendedoraDatabase::class.java,
                    nomeDoBanco
                ).build()
            }

            return db
        }
    }

}