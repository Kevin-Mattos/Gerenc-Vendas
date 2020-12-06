package com.example.vendasmae.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.vendasmae.baseClass.BaseAsyncTask
import com.example.vendasmae.entities.vendas.Venda
import com.example.vendasmae.entities.vendas.VendaDao
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.entities.vendas.VendaVendedoraItem
import com.example.vendasmae.repository.api.VendaApi
import com.example.vendasmae.repository.banco.VendaBanco
import retrofit2.Retrofit

class VendaRepository(private val vendaDao: VendaDao, retrofit: Retrofit) {

    val vendaApi =
        VendaApi(retrofit)
    val vendaBanco = VendaBanco(vendaDao)

    private val vendas = vendaBanco.getAll()


    private val mediatorLiveData = MediatorLiveData<List<VendaVendedoraItem>>()

    private val dataFilter = MutableLiveData<String>()
    init {
        dataFilter.value = ""
    }

    fun getAllVendas() = vendas

    fun getVendasEVendedoras(): MediatorLiveData<List<VendaVendedoraItem>> {
        mediatorLiveData.addSource(vendaBanco.getVendaEVendedora()){
            mediatorLiveData.value = it
        }
        mediatorLiveData.addSource(dataFilter){
            mediatorLiveData.addSource(vendaBanco.getFilteredDate(it)){
                mediatorLiveData.value = it
            }
        }


        return mediatorLiveData
    }

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

    fun getFilteredDate(date: String) {
        dataFilter.value = date
    }

}