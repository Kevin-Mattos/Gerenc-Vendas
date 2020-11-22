package com.example.vendasmae.banco.tipos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vendasmae.banco.itens.Item

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

    @Query("SELECT Tipo.*, COUNT(Item.id) as quantidadeEmEstoque, SUM(Item.valor) as somaDeValores  FROM Item, Tipo WHERE Tipo.id = Item.id_tipo group by Tipo.id")
    fun getTipoQuantidadeValor(): LiveData<List<TipoQuantidadeValor>>
}