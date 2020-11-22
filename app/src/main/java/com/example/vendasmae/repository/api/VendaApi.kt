package com.example.vendasmae.repository.api

import com.example.vendasmae.banco.vendas.Venda
import com.example.vendasmae.baseClass.BaseRequestCallBack
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.services.VendaService
import retrofit2.Retrofit

class VendaApi (retrofit: Retrofit) {

    val vendaService = retrofit.create(VendaService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Venda>?>) -> Unit, quandoFalha: (Resource<List<Venda>?>) -> Unit){
        val call = vendaService.buscarVenda()
        val callback = BaseRequestCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(item: Venda, quandoSucesso: (Resource<Venda?>) -> Unit, quandoFalha: (Resource<Venda?>) -> Unit){
        val call = vendaService.insereVenda(item)
        val callback = BaseRequestCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }

}