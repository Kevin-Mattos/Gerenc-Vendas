package com.example.vendasmae.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vendasmae.MainActivity
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.repository.MaletaRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MaletaFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val retrofit by lazy{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(MainActivity.baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    private val maletaRepo by lazy {
        val tipoDao = MainDataBase.getInstance(application).maletaDao()
        MaletaRepository(tipoDao, retrofit)
    }


    fun insere(tipo: Maleta) {
        maletaRepo.insere(tipo)
    }

    fun getMaletaQuantidadeValor() = maletaRepo.getMaletaQuantidadeValor()

}