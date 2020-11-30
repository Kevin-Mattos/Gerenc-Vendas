package com.example.vendasmae.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vendasmae.MainActivity
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.repository.ItemRepository
import com.example.vendasmae.repository.MaletaRepository
import com.example.vendasmae.repository.TipoRepository
import com.example.vendasmae.repository.VendedorasRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProdutoFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val retrofit by lazy{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(MainActivity.baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    lateinit var tipos: List<Tipo>
    lateinit var vendedoras: List<Vendedora>
    lateinit var maletas: List<Maleta>

     var selectedtipo: Tipo? = null
     var selectedVendedora: Vendedora? = null
    var selectedMaleta: Maleta? = null



    private val produtoRepo by lazy {
        val itemDao = MainDataBase.getInstance(application).itemDao()
        ItemRepository(itemDao, retrofit)
    }

    private val tipoRepo by lazy {
        val tipoDao = MainDataBase.getInstance(application).tipoDao()
        TipoRepository(tipoDao, retrofit)
    }

    private val vendedoraRepo by lazy {
        val tipoDao = MainDataBase.getInstance(application).vendedoraDao()
        VendedorasRepository(tipoDao, retrofit)
    }


    private val maletaRepo by lazy {
        val tipoDao = MainDataBase.getInstance(application).maletaDao()
        MaletaRepository(tipoDao, retrofit)
    }


    private val liveData = produtoRepo.getAll()

    fun get() = liveData

    fun getItemVendedora(id: Long) = produtoRepo.getItemVendedora(id)

    fun getItemMaleta(id: Long) = produtoRepo.getItemMaleta(id)

    fun insere(produto: Produto) {
        produtoRepo.insere(produto)
    }

    fun getTipos() = tipoRepo.getAll()

    fun getVendedoras() = vendedoraRepo.getAll()

    fun getMaletas() = maletaRepo.getAll()

    fun getSelectedVendedoraPos() =
        vendedoras.indexOf(selectedVendedora)

    fun getSelectedTipoPos() =
        tipos.indexOf(selectedtipo)

    fun update(produto: Produto) {
        produtoRepo.update(produto)
    }

    fun getSelectedMaletaPos() = maletas.indexOf(selectedMaleta)

}