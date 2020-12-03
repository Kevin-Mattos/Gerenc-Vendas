package com.example.vendasmae.entities.maleta

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MaletaDao {


    @Query("SELECT * FROM Maleta")
    fun getAll(): LiveData<List<Maleta>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMaleta(lista: Maleta)

    @Delete
    fun deleteMaleta(lista: Maleta)

    @Query("SELECT * FROM Maleta WHERE id == :id")
    fun getMaletaById(id: Long): LiveData<Maleta?>


    @Query("DELETE FROM Maleta")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(items: List<Maleta>)

    @Query("SELECT Maleta.*, COUNT(Produto.id) as quantidadeEmEstoque, SUM(Produto.valor) as somaDeValores  FROM Maleta LEFT JOIN Produto ON Maleta.id = Produto.id_maleta AND Produto.vendido = 0 group by Maleta.id;")//"SELECT Maleta.*, COUNT(Produto.id) as quantidadeEmEstoque, SUM(Produto.valor) as somaDeValores  FROM Maleta LEFT JOIN Produto ON Maleta.id = Produto.id_maleta AND Produto.vendido = 0 group by Maleta.id;")
    fun getMaletaQuantidadeValor(): LiveData<List<MaletaQuantidadeValor>?>

}
