package com.example.vendasmae.repository.api

import com.example.vendasmae.entities.vendas.Venda
import com.example.vendasmae.baseClass.BaseRequestCallback
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.services.VendaService
import retrofit2.Retrofit

class VendaApi (retrofit: Retrofit) {

    val vendaService = retrofit.create(VendaService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Venda>?>) -> Unit, quandoFalha: (Resource<List<Venda>?>) -> Unit){
        val call = vendaService.buscarVenda()
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(venda: Venda, quandoSucesso: (Resource<Venda?>) -> Unit, quandoFalha: (Resource<Venda?>) -> Unit){
        val call = vendaService.insereVenda(venda)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }

    fun atualiza(venda: Venda, quandoSucesso: (Resource<Venda?>) -> Unit, quandoFalha: (Resource<Venda?>) -> Unit) {
        val call = vendaService.atualizaVenda(venda, venda.id)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }

    fun remove(
        id: Long,
        quandoSucesso: (Resource<Long?>) -> Unit,
        quandoFalha: (Resource<Long?>) -> Unit
    ) {
        val call = vendaService.removeVenda(id)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }

    fun updateVenda(
        venda: Venda,
        quandoSucesso: (Resource<Venda?>) -> Unit,
        quandoFalha: (Resource<Venda?>) -> Unit
    ) {
        val call = vendaService.atualizaVenda(venda, venda.id)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }

}