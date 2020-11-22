package com.example.vendasmae.repository.services

import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.baseClass.Resource
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface VendedorasService{



    @GET("vendedora")
    fun buscarVendedoras(): Call<Resource<List<Vendedora>?>>

    @POST("vendedora")
    fun insereVendedora(@Body vendedora: Vendedora): Call<Resource<Vendedora?>>


}