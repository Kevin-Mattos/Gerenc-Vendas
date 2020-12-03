package com.example.vendasmae.repository.services

import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.baseClass.Resource
import retrofit2.Call
import retrofit2.http.*

private const val route = "tipo"
interface TipoService {
    @GET(route)
    fun buscarTipo(): Call<List<Tipo>?>

    @POST(route)
    fun insereTipo(@Body tipo: Tipo): Call<Tipo?>

    @PUT("$route/{id}")
    fun atualizaTipo(@Body tipo: Tipo,@Path("id") id: Long): Call<Tipo?>

    @DELETE("$route/{id}")
    fun removeTipo(@Path("id") id: Long): Call<Long?>

}