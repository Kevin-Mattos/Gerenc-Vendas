package com.example.vendasmae.repository.services

import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.baseClass.Resource
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val route = "tipo"
interface TipoService {
    @GET(route)
    fun buscarTipo(): Call<List<Tipo>?>

    @POST(route)
    fun insereTipo(@Body tipo: Tipo): Call<Tipo?>
}