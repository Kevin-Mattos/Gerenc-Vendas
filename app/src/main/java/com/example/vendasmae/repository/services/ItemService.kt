package com.example.vendasmae.repository.services

import com.example.vendasmae.entities.itens.Item
import com.example.vendasmae.baseClass.Resource
import retrofit2.Call
import retrofit2.http.*

private const val route = "item"
interface ItemService {

    @GET(route)
    fun Busca(): Call<Resource<List<Item>?>>

    @POST(route)
    fun insere(@Body item: Item): Call<Resource<Item?>>

    @PUT("$route/{id}")
    fun atualiza(@Body item: Item,@Path("id") id: Int): Call<Resource<Item?>>

}