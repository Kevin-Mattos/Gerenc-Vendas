package com.example.vendasmae.repository.services

import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.tipos.Tipo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val route = "maleta"
interface MaletaService {

    @GET(route)
    fun buscarMaleta(): Call<Resource<List<Maleta>?>>

    @POST(route)
    fun insereMaleta(@Body tipo: Maleta): Call<Resource<Maleta?>>
}