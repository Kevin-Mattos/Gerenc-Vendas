package com.example.vendasmae.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vendasmae.MainActivity
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.entities.itens.Item
import com.example.vendasmae.repository.ItemRepository
import com.example.vendasmae.repository.TipoRepository
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

    private val produtoRepo by lazy {
        val tipoDao = MainDataBase.getInstance(application).itemDao()
        ItemRepository(tipoDao, retrofit)
    }

    val liveData = produtoRepo.getAll()

    fun get() = liveData

    fun getItemVendedora(id: Long) = produtoRepo.getItemVendedora(id)
    fun insere(item: Item) {
        produtoRepo.insere(item)
    }

}