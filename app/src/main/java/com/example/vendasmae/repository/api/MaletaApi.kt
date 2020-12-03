package com.example.vendasmae.repository.api

import com.example.vendasmae.baseClass.BaseRequestCallback
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.repository.services.MaletaService
import retrofit2.Retrofit

class MaletaApi (retrofit: Retrofit) {

    val maletaService = retrofit.create(MaletaService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Maleta>?>) -> Unit, quandoFalha: (Resource<List<Maleta>?>) -> Unit){
        val call = maletaService.buscarMaleta()
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(maleta: Maleta, quandoSucesso: (Resource<Maleta?>) -> Unit, quandoFalha: (Resource<Maleta?>) -> Unit){
        val call = maletaService.insereMaleta(maleta)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }

    fun atualiza(
        maleta: Maleta,
        quandoSucesso: (Resource<Maleta?>) -> Unit,
        quandoFalha: (Resource<Maleta?>) -> Unit
    ) {
        val call = maletaService.atualizaMaleta(maleta.id, maleta)
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
        val call = maletaService.removeMaleta(id)
        val callback = BaseRequestCallback(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }
}