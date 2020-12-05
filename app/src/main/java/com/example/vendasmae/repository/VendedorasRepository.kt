package com.example.vendasmae.repository

import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.entities.vendedoras.VendedoraDao
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.api.VendedoraApi
import com.example.vendasmae.repository.banco.VendedoraBanco
import retrofit2.Retrofit

class VendedorasRepository(vendedoraDao: VendedoraDao, retrofit: Retrofit) {

    val vendedoraApi = VendedoraApi(retrofit)
    val vendedoraBanco = VendedoraBanco(vendedoraDao)

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

    private val liveData = vendedoraBanco.getVendedoraValorQuantidade()


    fun getVendedoraValorQuantidade() = liveData

    fun getAll() = vendedoraBanco.getAll()


    fun remove(vendedora: Vendedora) {
        val quandoSucesso: (Resource<Long?>) -> Unit = {
            vendedoraBanco.remove(it.dado!!)
        }
        val quandoFalha: (Resource<Long?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        vendedoraApi.remove(vendedora.id , quandoSucesso, quandoFalha)

    }

    fun updateVendedora(vendedora: Vendedora) {
        val quandoSucesso: (Resource<Vendedora?>) -> Unit = {
            vendedoraBanco.updateVendedora(it.dado!!)
        }
        val quandoFalha: (Resource<Vendedora?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        vendedoraApi.update(vendedora , quandoSucesso, quandoFalha)
    }
}

