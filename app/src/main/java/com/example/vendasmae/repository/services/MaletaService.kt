package com.example.vendasmae.repository.services

import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.tipos.Tipo
import retrofit2.Call
import retrofit2.http.*

private const val route = "maleta"
interface MaletaService {

    @GET(route)
    fun buscarMaleta(): Call<List<Maleta>?>

    @POST(route)
    fun insereMaleta(@Body tipo: Maleta): Call<Maleta?>

    @PUT("$route/{id}")
    fun atualizaMaleta(@Path("id") id: Long,@Body item: Maleta): Call<Maleta?>
}