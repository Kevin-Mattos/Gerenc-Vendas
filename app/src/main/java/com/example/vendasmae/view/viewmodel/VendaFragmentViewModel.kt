package com.example.vendasmae.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vendasmae.MainActivity
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.repository.VendaRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VendaFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val retrofit by lazy{
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(MainActivity.baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val vendedoraRepo by lazy {
        val itemDao = MainDataBase.getInstance(application).vendaDao()
        VendaRepository(itemDao, retrofit)
    }

    fun getVendasEVendedoras() = vendedoraRepo.getVendasEVendedoras()




}