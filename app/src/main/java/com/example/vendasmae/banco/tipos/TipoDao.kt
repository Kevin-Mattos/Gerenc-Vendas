package com.example.vendasmae.banco.tipos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vendasmae.banco.itens.Item

@Dao
interface TipoDao {

    @Query("SELECT * FROM Tipo")
    fun getAll(): LiveData<List<Tipo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLista(lista: Tipo)

    @Delete
    fun deleteLista(lista: Tipo)

    @Query("SELECT * FROM Item WHERE id == :id")
    fun getListaById(id: Long): LiveData<Tipo?>

    @Query("DELETE FROM Item WHERE id == :id")
    fun deleteListaById(id: Long)


    @Query("DELETE FROM Tipo")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(items: List<Tipo>)


}