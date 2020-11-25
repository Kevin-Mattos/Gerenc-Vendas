package com.example.vendasmae.repository.api

import com.example.vendasmae.baseClass.BaseRequestCallBack
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.repository.services.MaletaService
import retrofit2.Retrofit

class MaletaApi (retrofit: Retrofit) {

    val tipoService = retrofit.create(MaletaService::class.java)

    fun getAll(quandoSucesso: (Resource<List<Maleta>?>) -> Unit, quandoFalha: (Resource<List<Maleta>?>) -> Unit){
        val call = tipoService.buscarMaleta()
        val callback = BaseRequestCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }


    fun insere(item: Maleta, quandoSucesso: (Resource<Maleta?>) -> Unit, quandoFalha: (Resource<Maleta?>) -> Unit){
        val call = tipoService.insereMaleta(item)
        val callback = BaseRequestCallBack(
            quandoSucesso,
            quandoFalha
        )

        call.enqueue(callback.execute())
    }
}