package com.example.vendasmae.entities.itens

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")//WHERE item.id in (SELECT item.id FROM ITEM where item.id  not in (Select Venda.id_item from Venda))")
    fun getAll(): LiveData<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Produto)

    @Delete
    fun delete(item: Produto)

    @Query("DELETE FROM Produto WHERE id == :id")
    fun deleteProdutoById(id: Long)


    @Query("DELETE FROM Produto")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(produtos: List<Produto>)

    @Transaction
    @Query("SELECT * FROM Produto WHERE Produto.id_tipo == :id and Produto.vendido = :vendido")
    fun getProdutoEComQuemEsta(id: Long, vendido: Int = 0): LiveData<List<ProdutoVendedora>>

    @Transaction
    @Query("SELECT * FROM Produto WHERE Produto.id_maleta == :id and Produto.vendido = :vendido")
    fun getItemMaleta(id:Long, vendido: Int = 0): LiveData<List<ProdutoVendedora>>

    @Transaction
    @Query("SELECT *  FROM Produto left JOIN vendedora on Produto.id_vendedora = vendedora.id WHERE Produto.vendido = 0 group by vendedora.id")
    fun getProdutoEComQuemEsta(): LiveData<List<ProdutosVendedora>?>

}