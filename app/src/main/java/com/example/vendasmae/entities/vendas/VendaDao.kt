package com.example.vendasmae.entities.vendas

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VendaDao {

    @Query("SELECT * FROM Venda")
    fun getAll(): LiveData<List<Venda>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVenda(venda: Venda)

    @Delete
    fun deleteVenda(venda: Venda)


    @Query("DELETE FROM Venda")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(vendas: List<Venda>)

    @Transaction
    @Query("SELECT * FROM Venda")
    fun getVendaAndVendedora(): LiveData<List<VendaVendedoraItem>>



}