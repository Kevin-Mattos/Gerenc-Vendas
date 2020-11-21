package com.example.vendasmae.repository.services

import com.example.vendasmae.banco.itens.Item
import com.example.vendasmae.banco.vendas.Venda
import com.example.vendasmae.baseClass.Resource
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
private const val route = "venda"
interface VendaService {
    @GET(route)
    fun buscarVenda(): Call<Resource<List<Venda>?>>

    @POST(route)
    fun insereVenda(@Body venda: Venda): Call<Resource<Venda?>>
}