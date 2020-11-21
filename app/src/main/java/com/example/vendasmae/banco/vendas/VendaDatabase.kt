package com.example.vendasmae.banco.vendas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Venda::class], version = 1)
abstract class VendaDatabase: RoomDatabase() {
    abstract fun vendaDao(): VendaDao

    companion object {

        val nomeDoBanco = "venda.db"

        lateinit var db: VendaDatabase

        fun getInstance(context: Context): VendaDatabase {
            if(!::db.isInitialized){
                db = Room.databaseBuilder(
                    context,
                    VendaDatabase::class.java,
                    nomeDoBanco
                ).build()
            }

            return db
        }
    }

}