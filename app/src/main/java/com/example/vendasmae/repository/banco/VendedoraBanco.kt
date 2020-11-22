package com.example.vendasmae.repository.banco

import android.os.AsyncTask
import com.example.vendasmae.banco.vendedoras.Vendedora
import com.example.vendasmae.banco.vendedoras.VendedoraDao

class VendedoraBanco(val vendedoraDao: VendedoraDao) {

    fun getAll() = vendedoraDao.getAll()

    fun insert(vendedora: Vendedora){
        val executa = { vendedoraDao.insertLista(vendedora)}
        BaseAsyncTask(executa).execute()
    }

    fun removeAll() {
        val executa = { vendedoraDao.removeAll()}
        BaseAsyncTask(executa).execute()
    }

    fun insertMultiple(vendedoras: List<Vendedora>) {
        val executa = { vendedoraDao.insertMultiple(vendedoras)}
        BaseAsyncTask(executa).execute()
    }



}



class BaseAsyncTask<T>(private val executa: () -> T) :
    AsyncTask<T, Void, T>() {
    override fun doInBackground(vararg p0: T?) = executa()

}