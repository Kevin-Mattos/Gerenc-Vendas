package com.example.vendasmae.repository

import com.example.vendasmae.entities.vendas.Venda
import com.example.vendasmae.entities.vendas.VendaDao
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.api.VendaApi
import com.example.vendasmae.repository.banco.VendaBanco
import retrofit2.Retrofit

class VendaRepository(private val vendaDao: VendaDao, retrofit: Retrofit) {

    val vendaApi =
        VendaApi(retrofit)
    val vendaBanco = VendaBanco(vendaDao)

    private val vendas = vendaBanco.getAll()

    private val vendaEVendedeora = vendaBanco.getVendaEVendedora()

    fun getAllVendas() = vendas

    fun getVendasEVendedoras() = vendaEVendedeora

    fun buscarVendas() {

        val quandoSucesso: (Resource<List<Venda>?>) -> Unit = {
                vendaBanco.insertMultlple(it.dado!!)
        }
        val quandoFalha: (Resource<List<Venda>?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"

        }

        vendaApi.getAll(quandoSucesso, quandoFalha)
    }

    fun insere(venda: Venda){
        val quandoSucesso: (Resource<Venda?>) -> Unit = {
            vendaBanco.insert(it.dado!!)
        }
        val quandoFalha: (Resource<Venda?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        vendaApi.insere(venda, quandoSucesso, quandoFalha)

    }

    fun removeAll(){
        vendaBanco.removeAll()
    }

    fun atualiza(venda: Venda) {
        val quandoSucesso: (Resource<Venda?>) -> Unit = {
            vendaBanco.updateVenda(it.dado!!)
        }
        val quandoFalha: (Resource<Venda?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        vendaApi.atualiza(venda, quandoSucesso, quandoFalha)
    }

    fun remove(id: Long) {
        val quandoSucesso: (Resource<Long?>) -> Unit = {
            vendaBanco.removeById(it.dado!!)
        }
        val quandoFalha: (Resource<Long?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        vendaApi.remove(id, quandoSucesso, quandoFalha)
    }

}