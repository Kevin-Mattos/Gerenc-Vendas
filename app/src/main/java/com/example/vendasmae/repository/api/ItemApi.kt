package com.example.vendasmae.repository.api

import com.example.vendasmae.banco.itens.Item
import com.example.vendasmae.banco.vendedoras.Vendedora
import com.example.vendasmae.baseClass.BaseListCallBack
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.services.VendedorasService
import retrofit2.Retrofit

class ItemApi(retrofit: Retrofit) {

    val ItemService = retrofit.create(com.example.vendasmae.repository.services.ItemService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Item>?>) -> Unit, quandoFalha: (Resource<List<Item>?>) -> Unit){
        val call = ItemService.buscarVendedoras()
        val callback = BaseListCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(item: Item, quandoSucesso: (Resource<Item?>) -> Unit, quandoFalha: (Resource<Item?>) -> Unit){
        val call = ItemService.insereVendedora(item)
        val callback = BaseListCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }

}