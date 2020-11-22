package com.example.vendasmae.repository.api

import com.example.vendasmae.banco.vendedoras.Vendedora
import com.example.vendasmae.baseClass.BaseListCallBack
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.services.VendedorasService
import retrofit2.Retrofit

class VendedoraApi(retrofit: Retrofit) {

    val vendasServide = retrofit.create(VendedorasService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Vendedora>?>) -> Unit, quandoFalha: (Resource<List<Vendedora>?>) -> Unit){
        val call = vendasServide.buscarVendedoras()
        val callback = BaseListCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(vendedora: Vendedora, quandoSucesso: (Resource<Vendedora?>) -> Unit, quandoFalha: (Resource<Vendedora?>) -> Unit){
        val call = vendasServide.insereVendedora(vendedora)
        val callback = BaseListCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


}