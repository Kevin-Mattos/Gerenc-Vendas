package com.example.vendasmae.repository.services

import com.example.vendasmae.banco.vendedoras.Vendedora
import com.example.vendasmae.repository.Resource
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface VendedorasService{



    @GET("vendedora")
    fun buscarVendedoras(): Call<List<Vendedora>?>

    @POST("vendedora")
    fun insereVendedora(@Body vendedora: Vendedora): Call<Vendedora?>


}