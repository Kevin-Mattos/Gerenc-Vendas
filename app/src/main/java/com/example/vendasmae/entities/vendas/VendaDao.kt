package com.example.vendasmae.entities.vendas

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vendasmae.entities.maleta.Maleta

@Dao
interface VendaDao {

    @Query("SELECT * FROM Venda")
    fun getAll(): LiveData<List<Venda>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVenda(venda: Venda)

    @Delete
    fun deleteVenda(venda: Venda)


    @Update
    fun updateVenda(venda: Venda)

    @Query("DELETE FROM Venda")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(vendas: List<Venda>)

    @Transaction
    @Query("SELECT * FROM Venda")
    fun getVendaAndVendedora(): LiveData<List<VendaVendedoraItem>>

    @Query("DELETE FROM Venda WHERE Venda.id = :id")
    fun removeById(id: Long)

    @Query("SELECT * FROM Venda WHERE data > :date")
    fun getFilteredDate(date: String): LiveData<List<VendaVendedoraItem>>


}