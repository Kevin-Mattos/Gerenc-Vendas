package com.example.vendasmae.repository.services

import com.example.vendasmae.banco.itens.Item
import com.example.vendasmae.baseClass.Resource
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val route = "item"
interface ItemService {

    @GET(route)
    fun Busca(): Call<Resource<List<Item>?>>

    @POST(route)
    fun insere(@Body item: Item): Call<Resource<Item?>>

}