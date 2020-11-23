package com.example.vendasmae.repository

import android.util.Log
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.tipos.TipoDao
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.api.TipoApi
import com.example.vendasmae.repository.banco.TipoBanco
import retrofit2.Retrofit

class TipoRepository (itemDao: TipoDao, retrofit: Retrofit) {

    val itemApi =
        TipoApi(retrofit)
    val tipoBanco = TipoBanco(itemDao)

    private val liveData = tipoBanco.getAll()

    fun getAll() = liveData

    fun getTipoQuantidadeValor() = tipoBanco.getTipoQuantidadeValor()

    fun buscarTipos() {

        val quandoSucesso: (Resource<List<Tipo>?>) -> Unit = {
            tipoBanco.insertMultlple(it.dado!!)
        }
        val quandoFalha: (Resource<List<Tipo>?>) -> Unit = {
            Log.d("item", "falha na chamda")
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        itemApi.getAll(quandoSucesso, quandoFalha)
    }

    fun insere(tipo: Tipo){
        val quandoSucesso: (Resource<Tipo?>) -> Unit = {
            tipoBanco.insert(it.dado!!)
        }
        val quandoFalha: (Resource<Tipo?>) -> Unit = {
//            liveData.value = "Falha ao se comunicar"
        }

        itemApi.insere(tipo, quandoSucesso, quandoFalha)

    }

    fun removeAll(){
        tipoBanco.removeAll()
    }


}