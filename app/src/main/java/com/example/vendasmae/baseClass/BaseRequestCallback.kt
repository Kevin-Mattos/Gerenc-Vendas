package com.example.vendasmae.baseClass

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BaseRequestCallback<T>(val quandoSucesso: (Resource<T?>) -> Unit, val quandoFalha: (Resource<T?>) -> Unit) {

    fun execute(): Callback<T?> {
        val callback = object : Callback<T?> {
            override fun onFailure(call: Call<T?>, t: Throwable) {
                Log.d("request", "failed to get shit", t)

                quandoFalha(Resource(null, false, MyError(null, ResponseBody.create(null, "Sem comunicação"))))
            }

            override fun onResponse(
                call: Call<T?>,
                response: Response<T?>
            ) {
                Log.d("repo", "${response.isSuccessful}")
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it != null)
                            quandoSucesso(Resource(it, true))
                    }
                }else{
                    quandoFalha(Resource(null, false, MyError(response.code(), response.errorBody())))
                }
            }
        }
        return callback

    }
}