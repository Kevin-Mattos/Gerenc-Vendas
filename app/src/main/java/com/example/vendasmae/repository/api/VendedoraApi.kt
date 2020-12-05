package com.example.vendasmae.repository.api

import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.baseClass.BaseRequestCallback
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.services.VendedorasService
import retrofit2.Retrofit

class VendedoraApi(retrofit: Retrofit) {

    val vendedorasService = retrofit.create(VendedorasService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Vendedora>?>) -> Unit, quandoFalha: (Resource<List<Vendedora>?>) -> Unit){
        val call = vendedorasService.buscarVendedoras()
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(vendedora: Vendedora, quandoSucesso: (Resource<Vendedora?>) -> Unit, quandoFalha: (Resource<Vendedora?>) -> Unit){
        val call = vendedorasService.insereVendedora(vendedora)
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
        val call = vendedorasService.removeVendedora(id)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun update(vendedora: Vendedora, quandoSucesso: (Resource<Vendedora?>) -> Unit, quandoFalha: (Resource<Vendedora?>) -> Unit){
        val call = vendedorasService.updateVendedora(vendedora.id, vendedora)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


}