package com.example.vendasmae.banco.vendas

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vendasmae.banco.vendedoras.Vendedora

@Dao
interface VendaDao {

    @Query("SELECT * FROM Venda")
    fun getAll(): LiveData<List<Venda>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLista(lista: Venda)

    @Delete
    fun deleteLista(lista: Venda)

    @Query("SELECT * FROM Venda WHERE id == :id")
    fun getListaById(id: Long): LiveData<Venda?>

    @Query("DELETE FROM Venda WHERE id == :id")
    fun deleteListaById(id: Long)

    @Query("DELETE FROM Venda")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(vendas: List<Venda>)

}