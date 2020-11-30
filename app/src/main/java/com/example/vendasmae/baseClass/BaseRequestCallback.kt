package com.example.vendasmae.baseClass

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BaseRequestCallback<T>(val quandoSucesso: (Resource<T?>) -> Unit, val quandoFalha: (Resource<T?>) -> Unit) {

    fun execute(): Callback<Resource<T?>> {
        val callback = object : Callback<Resource<T?>> {
            override fun onFailure(call: Call<Resource<T?>>, t: Throwable) {
                Log.d("repo", "failed to get shit", t)

                quandoFalha(Resource(null, false, MyError(null, "No Internet")))
            }

            override fun onResponse(
                call: Call<Resource<T?>>,
                response: Response<Resource<T?>>
            ) {
                Log.d("repo", "${response.isSuccessful}")
                if (response.isSuccessful)
                    response.body()?.let {
                        if(it.dado != null)
                            quandoSucesso(it)
                    }
            }
        }
        return callback

    }
}