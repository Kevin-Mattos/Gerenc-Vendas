package com.example.vendasmae.repository.banco

import android.os.AsyncTask
import com.example.vendasmae.banco.vendedoras.Vendedora
import com.example.vendasmae.banco.vendedoras.VendedoraDao
import com.example.vendasmae.baseClass.BaseAsyncTask

class VendedoraBanco(val vendedoraDao: VendedoraDao) {

    fun getAll() = vendedoraDao.getAll()

    fun insert(vendedora: Vendedora){
        val executa = { vendedoraDao.insertVendedora(vendedora)}
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



    fun getVendedoraValorQuantidade() = vendedoraDao.getVendedoraValorQuantidade()


}

