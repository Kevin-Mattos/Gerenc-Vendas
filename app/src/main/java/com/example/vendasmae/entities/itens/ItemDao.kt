package com.example.vendasmae.entities.itens

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vendasmae.entities.vendas.VendaVendedoraItem

@Dao
interface ItemDao {

    @Query("SELECT * FROM Item")//WHERE item.id in (SELECT item.id FROM ITEM where item.id  not in (Select Venda.id_item from Venda))")
    fun getAll(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLista(lista: Item)

    @Delete
    fun deleteLista(lista: Item)

    @Query("SELECT * FROM Item WHERE id == :id")
    fun getListaById(id: Long): LiveData<Item?>

    @Query("DELETE FROM Item WHERE id == :id")
    fun deleteListaById(id: Long)


    @Query("DELETE FROM Item")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiple(items: List<Item>)

    @Transaction
    @Query("SELECT * FROM Item WHERE Item.id_tipo == :id")
    fun getProdutoEComQuemEsta(id: Long): LiveData<List<ItemVendedora>>


    @Transaction
    //@Query("SELECT Item.*, Vendedora.id AS vendorId, Vendedora.nome AS vendorName FROM VENDEDORA LEFT JOIN ITEM ON Item.id_vendedora = Vendedora.id  WHERE Item.id NOT IN (SELECT id_item from Venda)")
    @Query("SELECT *  FROM Item left JOIN vendedora on item.id_vendedora = vendedora.id WHERE ITEM.vendido = 0 group by vendedora.id")// LEFT JOIN Vendedora ON ITEM.id_vendedora = VENDEDORA.ID /*WHERE Item.id NOT IN (SELECT id_item from Venda) */")
    fun getProdutoEComQuemEsta(): LiveData<List<ItensVendedora>?>
}