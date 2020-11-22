package com.example.vendasmae.repository.api

import com.example.vendasmae.banco.tipos.Tipo
import com.example.vendasmae.baseClass.BaseListCallBack
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.services.TipoService
import retrofit2.Retrofit

class TipoApi (retrofit: Retrofit) {

    val tipoService = retrofit.create(TipoService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Tipo>?>) -> Unit, quandoFalha: (Resource<List<Tipo>?>) -> Unit){
        val call = tipoService.buscarVenda()
        val callback = BaseListCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(item: Tipo, quandoSucesso: (Resource<Tipo?>) -> Unit, quandoFalha: (Resource<Tipo?>) -> Unit){
        val call = tipoService.insereVenda(item)
        val callback = BaseListCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }
}