package com.example.vendasmae.repository.api

import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.baseClass.BaseRequestCallback
import com.example.vendasmae.baseClass.Resource
import retrofit2.Retrofit

class ItemApi(retrofit: Retrofit) {

    val ItemService = retrofit.create(com.example.vendasmae.repository.services.ItemService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Produto>?>) -> Unit, quandoFalha: (Resource<List<Produto>?>) -> Unit){
        val call = ItemService.Busca()
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(produto: Produto, quandoSucesso: (Resource<Produto?>) -> Unit, quandoFalha: (Resource<Produto?>) -> Unit){
        val call = ItemService.insere(produto)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }

    fun atualiza(produto: Produto, quandoSucesso: (Resource<Produto?>) -> Unit, quandoFalha: (Resource<Produto?>) -> Unit) {

        val call = ItemService.atualiza(produto, produto.id.toInt())
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())

    }

}