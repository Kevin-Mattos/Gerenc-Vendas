package com.example.vendasmae.baseClass

import android.os.AsyncTask


class BaseAsyncTask<T>(private val executa: () -> T) :
    AsyncTask<T, Void, T>() {
    override fun doInBackground(vararg p0: T?) = executa()

}