package com.example.vendasmae.entities.maleta

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vendasmae.entities.itens.Item
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.tipos.TipoQuantidadeValor

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

    @Query("SELECT Maleta.*, COUNT(Item.id) as quantidadeEmEstoque, SUM(Item.valor) as somaDeValores  FROM Maleta LEFT JOIN Item ON Maleta.id = Item.id_maleta group by Maleta.id;")
    fun getMaletaQuantidadeValor(): LiveData<List<MaletaQuantidadeValor>?>

}