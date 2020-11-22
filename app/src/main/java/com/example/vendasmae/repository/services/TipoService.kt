package com.example.vendasmae.repository.services

import com.example.vendasmae.banco.tipos.Tipo
import com.example.vendasmae.banco.vendas.Venda
import com.example.vendasmae.baseClass.Resource
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val route = "tipo"
interface TipoService {
    @GET(route)
    fun buscarVenda(): Call<Resource<List<Tipo>?>>

    @POST(route)
    fun insereVenda(@Body tipo: Tipo): Call<Resource<Tipo?>>
}