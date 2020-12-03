package com.example.vendasmae.repository.api

import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.baseClass.BaseRequestCallback
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.services.TipoService
import retrofit2.Retrofit

class TipoApi (retrofit: Retrofit) {

    val tipoService = retrofit.create(TipoService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Tipo>?>) -> Unit, quandoFalha: (Resource<List<Tipo>?>) -> Unit){
        val call = tipoService.buscarTipo()
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(tipo: Tipo, quandoSucesso: (Resource<Tipo?>) -> Unit, quandoFalha: (Resource<Tipo?>) -> Unit){
        val call = tipoService.insereTipo(tipo)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }

    fun update(
        tipo: Tipo,
        quandoSucesso: (Resource<Tipo?>) -> Unit,
        quandoFalha: (Resource<Tipo?>) -> Unit
    ) {

        val call = tipoService.atualizaTipo(tipo, tipo.id)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())

    }

    fun remove(
        tipo: Tipo,
        quandoSucesso: (Resource<Long?>) -> Unit,
        quandoFalha: (Resource<Long?>) -> Unit
    ) {
        val call = tipoService.removeTipo(tipo.id)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }
}