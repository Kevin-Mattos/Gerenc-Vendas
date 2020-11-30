package com.example.vendasmae.repository.services

import com.example.vendasmae.entities.vendas.Venda
import com.example.vendasmae.baseClass.Resource
import retrofit2.Call
import retrofit2.http.*

private const val route = "venda"
interface VendaService {
    @GET(route)
    fun buscarVenda(): Call<List<Venda>?>

    @POST(route)
    fun insereVenda(@Body venda: Venda): Call<Venda?>

    @PUT("$route/{id}")
    fun atualizaVenda(@Body venda: Venda, @Path("id") id: Long): Call<Venda?>
}