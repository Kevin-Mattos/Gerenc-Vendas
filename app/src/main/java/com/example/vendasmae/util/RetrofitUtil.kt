package com.example.vendasmae.util

import com.example.vendasmae.MainActivity
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtil {

    companion object{
        fun getRetrofit(): Retrofit {

                val gson = GsonBuilder()
                    .setLenient()
                    .serializeNulls()
                    .create()

                return Retrofit.Builder()
                    .baseUrl(MainActivity.baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

        }
    }
}