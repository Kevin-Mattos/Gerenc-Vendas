package com.example.vendasmae.repository.services

import com.example.vendasmae.banco.itens.Item
import com.example.vendasmae.banco.vendedoras.Vendedora
import com.example.vendasmae.baseClass.Resource
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val route = "item"
interface ItemService {



    @GET(route)
    fun buscarVendedoras(): Call<Resource<List<Item>?>>

    @POST(route)
    fun insereVendedora(@Body item: Item): Call<Resource<Item?>>

}