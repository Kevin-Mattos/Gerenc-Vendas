package com.example.vendasmae.repository.banco

import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.itens.ProdutoDao
import com.example.vendasmae.baseClass.BaseAsyncTask

class ProdutoBanco(private val produtoDao: ProdutoDao) {


    fun getAll() = produtoDao.getAll()

    fun insert(produto: Produto){
        val executa = { produtoDao.insert(produto)}
        BaseAsyncTask(executa).execute()
    }

    fun removeAll() {
        val executa = { produtoDao.removeAll()}
        BaseAsyncTask(executa).execute()
    }

    fun insertMultlple(produtos: List<Produto>) {
        val executa = { produtoDao.insertMultiple(produtos)}
        BaseAsyncTask(executa).execute()
    }

    fun getProdutoVendedora(id: Long) =  produtoDao.getProdutoEComQuemEsta(id)
    fun getProdutoMaleta(id: Long) = produtoDao.getItemMaleta(id)

    fun getProdutoVendedora() =  produtoDao.getProdutoEComQuemEsta()

}