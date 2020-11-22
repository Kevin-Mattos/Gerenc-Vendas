package com.example.vendasmae.repository.api

import com.example.vendasmae.banco.itens.Item
import com.example.vendasmae.baseClass.BaseRequestCallBack
import com.example.vendasmae.baseClass.Resource
import retrofit2.Retrofit

class ItemApi(retrofit: Retrofit) {

    val ItemService = retrofit.create(com.example.vendasmae.repository.services.ItemService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Item>?>) -> Unit, quandoFalha: (Resource<List<Item>?>) -> Unit){
        val call = ItemService.Busca()
        val callback = BaseRequestCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(item: Item, quandoSucesso: (Resource<Item?>) -> Unit, quandoFalha: (Resource<Item?>) -> Unit){
        val call = ItemService.insere(item)
        val callback = BaseRequestCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }

}