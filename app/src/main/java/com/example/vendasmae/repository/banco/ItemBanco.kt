package com.example.vendasmae.repository.banco

import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.itens.ProdutoDao
import com.example.vendasmae.baseClass.BaseAsyncTask

class ItemBanco(private val produtoDao: ProdutoDao) {


    fun getAll() = produtoDao.getAll()

    fun insert(produto: Produto){
        val executa = { produtoDao.insertLista(produto)}
        BaseAsyncTask(executa).execute()
    }

    fun removeAll() {
        val executa = { produtoDao.removeAll()}
        BaseAsyncTask(executa).execute()
    }

    fun insertMultlple(itens: List<Produto>) {
        val executa = { produtoDao.insertMultiple(itens)}
        BaseAsyncTask(executa).execute()
    }

    fun getItemVendedora(id: Long) =  produtoDao.getProdutoEComQuemEsta(id)
    fun getItemMaleta(id: Long) = produtoDao.getItemMaleta(id)

    fun getItemVendedora() =  produtoDao.getProdutoEComQuemEsta()

}