package com.example.vendasmae.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.vendasmae.MainActivity
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.entities.vendedoras.VendedoraQuantidadeValor
import com.example.vendasmae.repository.VendedorasRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VendedoraFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val retrofit by lazy{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(MainActivity.baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val vendedoraRepo by lazy {
        val itemDao = MainDataBase.getInstance(application).vendedoraDao()
       VendedorasRepository(itemDao, retrofit)
    }

    val liveData = vendedoraRepo.getVendedoraValorQuantidade()

    fun getVendedoraValorQuantidade() = liveData

    fun getQTD() = liveData.value?.size

    fun insere(vendedora: Vendedora) = vendedoraRepo.insere(vendedora)
}