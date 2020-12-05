package com.example.vendasmae.entities.vendedoras

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vendasmae.entities.maleta.Maleta

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


    @Update
    fun updateVendedora(vendedora: Vendedora)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(vendedoras: List<Vendedora>)


    @Query("SELECT vendedora.*, COUNT(Venda.id) as quantidadeVentido, SUM(Venda.valor) as valorVendido  FROM Vendedora LEFT JOIN Venda ON vendedora.id = venda.id_vendedora group by Vendedora.id;")
    fun getVendedoraValorQuantidade(): LiveData<List<VendedoraQuantidadeValor>>

    @Query("SELECT vendedora.*, COUNT(Venda.id) as quantidadeVentido, SUM(Venda.valor) as valorVendido  FROM Vendedora LEFT JOIN Venda ON vendedora.id = venda.id_vendedora group by Vendedora.id ORDER BY quantidadeVentido DESC;")
    fun getVendedoraValorQuantidadeOrderByVendas(): LiveData<List<VendedoraQuantidadeValor>>

    @Query("DELETE FROM Vendedora WHERE id = :id")
    fun removeVendedora(id: Long)

    @Query("SELECT vendedora.*, COUNT(Venda.id) as quantidadeVentido, SUM(Venda.valor) as valorVendido  FROM Vendedora LEFT JOIN Venda ON vendedora.id = venda.id_vendedora group by Vendedora.id ORDER BY valorVendido DESC;")
    fun getVendedoraValorQuantidadeOrderByValorVendido(): LiveData<List<VendedoraQuantidadeValor>>


}