package com.example.vendasmae.repository.banco

import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.itens.ItemDao
import com.example.vendasmae.baseClass.BaseAsyncTask

class ItemBanco(private val itemDao: ItemDao) {


    fun getAll() = itemDao.getAll()

    fun insert(produto: Produto){
        val executa = { itemDao.insertLista(produto)}
        BaseAsyncTask(executa).execute()
    }

    fun removeAll() {
        val executa = { itemDao.removeAll()}
        BaseAsyncTask(executa).execute()
    }

    fun insertMultlple(itens: List<Produto>) {
        val executa = { itemDao.insertMultiple(itens)}
        BaseAsyncTask(executa).execute()
    }

    fun getItemVendedora(id: Long) =  itemDao.getProdutoEComQuemEsta(id)
    fun getItemMaleta(id: Long) = itemDao.getItemMaleta(id)

    fun getItemVendedora() =  itemDao.getProdutoEComQuemEsta()

}