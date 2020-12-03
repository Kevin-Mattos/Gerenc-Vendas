package com.example.vendasmae.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vendasmae.MainActivity
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.repository.MaletaRepository
import com.example.vendasmae.repository.VendedorasRepository
import com.example.vendasmae.util.RetrofitUtil
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MaletaFragmentViewModel(application: Application): AndroidViewModel(application) {


    private val retrofit = RetrofitUtil.getRetrofit()


    lateinit var vendedoras: List<Vendedora>
    var selectedVendedora: Vendedora? = null

    private val maletaRepo by lazy {
        val tipoDao = MainDataBase.getInstance(application).maletaDao()
        MaletaRepository(tipoDao, retrofit)
    }

    private val vendedoraRepo by lazy {
        val tipoDao = MainDataBase.getInstance(application).vendedoraDao()
        VendedorasRepository(tipoDao, retrofit)
    }


    fun insere(tipo: Maleta) {
        maletaRepo.insere(tipo)
    }

    fun getMaletaQuantidadeValor() = maletaRepo.getMaletaQuantidadeValor()

    fun update(maleta: Maleta) {
        maletaRepo.update(maleta)
    }


    fun getSelectedVendedoraPosition(): Int{
        return if(selectedVendedora == null)
            0
        else
            vendedoras.indexOf(selectedVendedora!!) + 1

    }
    fun getVendedoras() = vendedoraRepo.getAll()


}