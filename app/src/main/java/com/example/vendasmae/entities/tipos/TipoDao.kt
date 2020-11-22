package com.example.vendasmae.entities.tipos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TipoDao {

    @Query("SELECT * FROM Tipo")
    fun getAll(): LiveData<List<Tipo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTipo(lista: Tipo)

    @Delete
    fun deleteTipo(lista: Tipo)

    @Query("DELETE FROM Tipo")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(items: List<Tipo>)

    @Query("SELECT Tipo.*, COUNT(Item.id) as quantidadeEmEstoque, SUM(Item.valor) as somaDeValores  FROM Tipo LEFT JOIN Item ON Tipo.id = Item.id_tipo group by Tipo.id;")
    fun getTipoQuantidadeValor(): LiveData<List<TipoQuantidadeValor>>
}