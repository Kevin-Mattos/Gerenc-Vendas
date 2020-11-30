package com.example.vendasmae.repository.services

import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.baseClass.Resource
import retrofit2.Call
import retrofit2.http.*

private const val route = "produto"
interface ItemService {

    @GET(route)
    fun Busca(): Call<List<Produto>?>

    @POST(route)
    fun insere(@Body produto: Produto): Call<Produto?>

    @PUT("$route/{id}")
    fun atualiza(@Body produto: Produto, @Path("id") id: Int): Call<Produto?>

}