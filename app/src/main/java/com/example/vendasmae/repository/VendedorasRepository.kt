package com.example.vendasmae.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vendasmae.banco.vendedoras.Vendedora
import com.example.vendasmae.banco.vendedoras.VendedoraDao
import com.example.vendasmae.baseClass.MyError
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.api.VendedoraApi
import com.example.vendasmae.repository.banco.VendedoraBanco
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class VendedorasRepository(vendedoraDao: VendedoraDao, retrofit: Retrofit) {

    val vendedoraApi =
        VendedoraApi(retrofit)
    val vendedoraBanco = VendedoraBanco(vendedoraDao)

    private val liveData = vendedoraBanco.getAll()

    fun getAll() = liveData

    fun buscarVendedoras() {

        val quandoSucesso: (Resource<List<Vendedora>?>) -> Unit = {

                vendedoraBanco.insertMultiple(it.dado!!)

        }
        val quandoFalha: (Resource<List<Vendedora>?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        vendedoraApi.getAll(quandoSucesso, quandoFalha)
    }

    fun insere(vendedora: Vendedora){
        val quandoSucesso: (Resource<Vendedora?>) -> Unit = {
            vendedoraBanco.insert(it.dado!!)
        }
        val quandoFalha: (Resource<Vendedora?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        vendedoraApi.insere(vendedora, quandoSucesso, quandoFalha)

    }

    fun removeAll(){
        vendedoraBanco.removeAll()
    }



}


class BaseListCallBack<T>(val quandoSucesso: (Resource<T?>) -> Unit, val quandoFalha: (Resource<T?>) -> Unit) {

    fun execute(): Callback<Resource<T?>> {
        val callback = object : Callback<Resource<T?>> {
            override fun onFailure(call: Call<Resource<T?>>, t: Throwable) {
                Log.d("repo", "failed to get shit", t)

                quandoFalha(Resource(null, false, MyError(null, "No Internet")))
            }

            override fun onResponse(
                call: Call<Resource<T?>>,
                response: Response<Resource<T?>>
            ) {
                Log.d("repo", "${response.isSuccessful}")
                if (response.isSuccessful)
                    response.body()?.let {
                        if(it.dado != null)
                            quandoSucesso(it)
                    }
            }
        }
        return callback

    }
}