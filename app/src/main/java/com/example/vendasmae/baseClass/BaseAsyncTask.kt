package com.example.vendasmae.baseClass

import android.os.AsyncTask


class BaseAsyncTask<T>(private val executa: () -> T, private val termina: (T) -> Unit = {}) :
    AsyncTask<T, Void, T>() {
    override fun doInBackground(vararg p0: T?) = executa()

    override fun onPostExecute(result: T) {
        super.onPostExecute(result)
        termina(result)
    }


}