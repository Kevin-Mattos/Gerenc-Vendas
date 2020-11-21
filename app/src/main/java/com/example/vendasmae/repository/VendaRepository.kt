package com.example.vendasmae.repository

import com.example.vendasmae.banco.vendas.Venda
import com.example.vendasmae.banco.vendas.VendaDao
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.api.VendaApi
import com.example.vendasmae.repository.banco.VendaBanco
import retrofit2.Retrofit

class VendaRepository(private val vendaDao: VendaDao, retrofit: Retrofit) {

    val vendaApi =
        VendaApi(retrofit)
    val vendaBanco = VendaBanco(vendaDao)

    private val liveData = vendaBanco.getAll()

    fun getAll() = liveData

    fun buscarVendas() {

        val quandoSucesso: (Resource<List<Venda>?>) -> Unit = {
                vendaBanco.insertMultlple(it.dado!!)
        }
        val quandoFalha: (Resource<List<Venda>?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        vendaApi.getAll(quandoSucesso, quandoFalha)
    }

    fun insere(vendedora: Venda){
        val quandoSucesso: (Resource<Venda?>) -> Unit = {
            vendaBanco.insert(it.dado!!)
        }
        val quandoFalha: (Resource<Venda?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        vendaApi.insere(vendedora, quandoSucesso, quandoFalha)

    }

    fun removeAll(){
        vendaBanco.removeAll()
    }

}