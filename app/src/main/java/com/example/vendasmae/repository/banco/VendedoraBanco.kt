package com.example.vendasmae.repository.banco

import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.entities.vendedoras.VendedoraDao
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

    fun remove(id: Long) {
        val executa = { vendedoraDao.removeVendedora(id)}
        BaseAsyncTask(executa).execute()
    }

    fun updateVendedora(vendedora: Vendedora) {
        val executa = { vendedoraDao.updateVendedora(vendedora)}
        BaseAsyncTask(executa).execute()
    }


}

