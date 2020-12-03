package com.example.vendasmae.entities.tipos

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TipoDao {

    @Query("SELECT * FROM Tipo")
    fun getAll(): LiveData<List<Tipo>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTipo(lista: Tipo)

    @Delete
    fun deleteTipo(lista: Tipo)

    @Query("DELETE FROM Tipo")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(items: List<Tipo>)

    @Query("SELECT Tipo.*, COUNT(Produto.id) as quantidadeEmEstoque, SUM(Produto.valor) as somaDeValores  FROM Tipo LEFT JOIN Produto ON Tipo.id = Produto.id_tipo and Produto.vendido = 0 group by Tipo.id;")
    fun getTipoQuantidadeValor(): LiveData<List<TipoQuantidadeValor>?>

    @Query("DELETE FROM Tipo WHERE Tipo.id = :id")
    fun removeById(id: Long)
}