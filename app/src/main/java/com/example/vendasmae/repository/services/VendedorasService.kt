package com.example.vendasmae.repository.services

import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.baseClass.Resource
import retrofit2.Call
import retrofit2.http.*


interface VendedorasService{



    @GET("vendedora")
    fun buscarVendedoras(): Call<List<Vendedora>?>

    @POST("vendedora")
    fun insereVendedora(@Body vendedora: Vendedora): Call<Vendedora?>


    @DELETE("vendedora/{id}")
    fun removeVendedora(@Path("id") id: Long): Call<Long?>


    @PUT("vendedora/{id}")
    fun updateVendedora(@Path("id") id: Long, @Body vendedora: Vendedora): Call<Vendedora?>

}