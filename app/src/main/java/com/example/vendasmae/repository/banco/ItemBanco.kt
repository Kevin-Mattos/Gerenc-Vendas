package com.example.vendasmae.repository.banco

import com.example.vendasmae.banco.itens.Item
import com.example.vendasmae.banco.itens.ItemDao
import com.example.vendasmae.banco.vendedoras.Vendedora

class ItemBanco(private val itemDao: ItemDao) {


    fun getAll() = itemDao.getAll()

    fun insert(item: Item){
        val executa = { itemDao.insertLista(item)}
        BaseAsyncTask(executa).execute()
    }

    fun removeAll() {
        val executa = { itemDao.removeAll()}
        BaseAsyncTask(executa).execute()
    }

    fun insertMultlple(itens: List<Item>) {
        val executa = { itemDao.insertMultiple(itens)}
        BaseAsyncTask(executa).execute()
    }

}