package com.example.vendasmae.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vendasmae.entities.itens.Item
import com.example.vendasmae.entities.itens.ItemDao
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.tipos.TipoDao
import com.example.vendasmae.entities.vendas.Venda
import com.example.vendasmae.entities.vendas.VendaDao
import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.entities.vendedoras.VendedoraDao

@Database(entities = [Vendedora::class, Item::class, Venda::class, Tipo::class], version = 1, exportSchema = false)
abstract class MainDataBase: RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun vendaDao(): VendaDao
    abstract fun vendedoraDao(): VendedoraDao
    abstract fun tipoDao(): TipoDao

    companion object {

        val nomeDoBanco = "item.db"

        lateinit var db: MainDataBase

        fun getInstance(context: Context): MainDataBase {
            if(!Companion::db.isInitialized){
                db = Room.databaseBuilder(
                    context,
                    MainDataBase::class.java,
                    nomeDoBanco
                ).build()
            }

            return db
        }
    }

}