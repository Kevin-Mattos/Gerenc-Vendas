package com.example.vendasmae.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vendasmae.MainActivity
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.repository.TipoRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TipoFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val retrofit by lazy{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(MainActivity.baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    private val tipoRepo by lazy {
        val tipoDao = MainDataBase.getInstance(application).tipoDao()
        TipoRepository(tipoDao, retrofit)
    }


    val liveData = tipoRepo.getTipoQuantidadeValor()
    fun getTipoQuantidadeValor() = liveData
    fun getQuantidade() = liveData.value?.size


    fun insere(tipo: Tipo) {
        tipoRepo.insere(tipo)
    }


}