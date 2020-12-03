package com.example.vendasmae.repository.api

import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.baseClass.BaseRequestCallback
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.services.ProdutoService
import retrofit2.Retrofit

class ProdutoApi(retrofit: Retrofit) {

    val produtoService = retrofit.create(ProdutoService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Produto>?>) -> Unit, quandoFalha: (Resource<List<Produto>?>) -> Unit){
        val call = produtoService.Busca()
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(produto: Produto, quandoSucesso: (Resource<Produto?>) -> Unit, quandoFalha: (Resource<Produto?>) -> Unit){
        val call = produtoService.insere(produto)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }

    fun atualiza(produto: Produto, quandoSucesso: (Resource<Produto?>) -> Unit, quandoFalha: (Resource<Produto?>) -> Unit) {

        val call = produtoService.atualiza(produto, produto.id.toInt())
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())

    }

}