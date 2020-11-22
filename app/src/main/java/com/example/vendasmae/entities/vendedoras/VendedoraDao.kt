package com.example.vendasmae.entities.vendedoras

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VendedoraDao {

    @Query("SELECT * FROM Vendedora")
    fun getAll(): LiveData<List<Vendedora>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVendedora(vendedoras: Vendedora)

    @Delete
    fun deleteVendedora(vendedoras: Vendedora)

    @Query("DELETE FROM Vendedora")
    fun removeAll()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(vendedoras: List<Vendedora>)


    @Query("SELECT vendedora.*, COUNT(Venda.id) as quantidadeVentido, SUM(Venda.valor) as valorVendido  FROM Vendedora LEFT JOIN Venda ON vendedora.id = venda.id_vendedora group by Vendedora.id;")
    fun getVendedoraValorQuantidade(): LiveData<List<VendedoraQuantidadeValor>>



}