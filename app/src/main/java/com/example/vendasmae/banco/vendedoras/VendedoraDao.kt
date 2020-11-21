package com.example.vendasmae.banco.vendedoras

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VendedoraDao {

    @Query("SELECT * FROM Vendedora")
    fun getAll(): LiveData<List<Vendedora>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLista(lista: Vendedora)

    @Delete
    fun deleteLista(lista: Vendedora)

    @Query("SELECT * FROM Vendedora WHERE id == :id")
    fun getListaById(id: Long): LiveData<Vendedora?>

    @Query("DELETE FROM Vendedora WHERE id == :id")
    fun deleteListaById(id: Long)

    @Query("DELETE FROM Vendedora")
    fun removeAll()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(vendedoras: List<Vendedora>)


}