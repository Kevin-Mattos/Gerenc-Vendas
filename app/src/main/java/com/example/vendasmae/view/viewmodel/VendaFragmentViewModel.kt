package com.example.vendasmae.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vendasmae.MainActivity
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.entities.itens.Item
import com.example.vendasmae.entities.itens.ItensVendedora
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.vendas.Venda
import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.repository.ItemRepository
import com.example.vendasmae.repository.VendaRepository
import com.example.vendasmae.repository.VendedorasRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VendaFragmentViewModel(application: Application): AndroidViewModel(application) {

    var itensVendedora: List<ItensVendedora>? = null

    var selectedVendedora: Vendedora? = null

    var selectedItem: Item? = null

    lateinit var itens: List<Item>

    lateinit var vendedoras: List<Vendedora>


    private val retrofit by lazy{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(MainActivity.baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val vendaRepo by lazy {
        val itemDao = MainDataBase.getInstance(application).vendaDao()
        VendaRepository(itemDao, retrofit)
    }

    private val itemRepo by lazy {
        val itemDao = MainDataBase.getInstance(application).itemDao()
        ItemRepository(itemDao, retrofit)
    }

    private val vendedoraRepo by lazy {
        val itemDao = MainDataBase.getInstance(application).vendedoraDao()
        VendedorasRepository(itemDao, retrofit)
    }


    val liveData = vendaRepo.getVendasEVendedoras()

    fun getVendasEVendedoras() = liveData

    fun getQuantidade() = liveData.value?.size

    fun insere(venda: Venda) {
        vendaRepo.insere(venda)
    }

    fun getSelectedItemPos(): Int {
        return itens?.indexOfFirst { it?.id == selectedItem?.id }?:0
    }

    fun getSelectedVendedoraPos(): Int {
        return vendedoras?.indexOfFirst { it?.id == selectedVendedora?.id }?:0
    }

    fun getAllVendedoras() = vendedoraRepo.getAll()

    fun getAllItens() = itemRepo.getAll()
    fun atualiza(venda: Venda) {
        vendaRepo.atualiza(venda)
    }

    fun getItemVendedor() = itemRepo.getProdutoEComQuemEsta()


}