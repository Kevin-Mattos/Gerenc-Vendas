package com.example.vendasmae.banco.itens

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vendasmae.banco.vendedoras.Vendedora

@Dao
interface ItemDao {

    @Query("SELECT * FROM Item")
    fun getAll(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLista(lista: Item)

    @Delete
    fun deleteLista(lista: Item)

    @Query("SELECT * FROM Item WHERE id == :id")
    fun getListaById(id: Long): LiveData<Item?>

    @Query("DELETE FROM Item WHERE id == :id")
    fun deleteListaById(id: Long)


    @Query("DELETE FROM Item")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(items: List<Item>)

}