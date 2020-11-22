package com.example.vendasmae.repository.api

import com.example.vendasmae.banco.tipos.Tipo
import com.example.vendasmae.baseClass.BaseRequestCallBack
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.services.TipoService
import retrofit2.Retrofit

class TipoApi (retrofit: Retrofit) {

    val tipoService = retrofit.create(TipoService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Tipo>?>) -> Unit, quandoFalha: (Resource<List<Tipo>?>) -> Unit){
        val call = tipoService.buscarTipo()
        val callback = BaseRequestCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(item: Tipo, quandoSucesso: (Resource<Tipo?>) -> Unit, quandoFalha: (Resource<Tipo?>) -> Unit){
        val call = tipoService.insereTipo(item)
        val callback = BaseRequestCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }
}