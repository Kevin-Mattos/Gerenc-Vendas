package com.example.vendasmae.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.itens.ProdutoDao
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.maleta.MaletaDao
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.tipos.TipoDao
import com.example.vendasmae.entities.vendas.Venda
import com.example.vendasmae.entities.vendas.VendaDao
import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.entities.vendedoras.VendedoraDao

@Database(entities = [Vendedora::class, Produto::class, Venda::class, Tipo::class, Maleta::class], version = 1, exportSchema = false)
abstract class MainDataBase: RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
    abstract fun vendaDao(): VendaDao
    abstract fun vendedoraDao(): VendedoraDao
    abstract fun tipoDao(): TipoDao
    abstract fun maletaDao(): MaletaDao

    companion object {

        val nomeDoBanco = "gerenci.db"

        lateinit var db: MainDataBase

        fun getInstance(context: Context): MainDataBase {
            if(!Companion::db.isInitialized){
                db = Room.databaseBuilder(
                    context,
                    MainDataBase::class.java,
                    nomeDoBanco
                ).build()
                var query =
                    "create trigger IF NOT EXISTS atualizaVenda after insert on venda " +
                            "begin " +
                            "UPDATE Produto SET vendido = 1 WHERE new.id_produto = Produto.id; " +
                            "end"
                val triggerOnInsertVenda = db.openHelper
                triggerOnInsertVenda.writableDatabase.execSQL(query)

                query =
                    "create trigger IF NOT EXISTS transfereMaleta after update on Maleta" +
                            " BEGIN " +
                            "UPDATE item SET id_vendedora = new.id_vendedora WHERE id_maleta = new.id;" +
                            " end;"
                val triggerOnUpdateMaleta = db.openHelper
                triggerOnUpdateMaleta.writableDatabase.execSQL(query)

                query =
                    "create trigger IF NOT EXISTS transfereItemOnDeleteMaleta after delete on Maleta" +
                            " BEGIN " +
                            "UPDATE produto SET id_maleta = null WHERE id_maleta = old.id;" +
                            " end;"

                val triggerOnDeleteMaleta = db.openHelper
                triggerOnDeleteMaleta.writableDatabase.execSQL(query)


            }

            return db
        }
    }

}