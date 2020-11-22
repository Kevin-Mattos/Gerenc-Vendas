package com.example.vendasmae.repository.banco

import com.example.vendasmae.entities.vendas.Venda
import com.example.vendasmae.entities.vendas.VendaDao
import com.example.vendasmae.baseClass.BaseAsyncTask

class VendaBanco (val vendaDao: VendaDao) {

    fun getAll() = vendaDao.getAll()

    fun insert(vendedora: Venda){
        val executa = { vendaDao.insertVenda(vendedora)}
        BaseAsyncTask(executa).execute()
    }

    fun removeAll() {
        val executa = { vendaDao.removeAll()}
        BaseAsyncTask(executa).execute()
    }

    fun insertMultlple(vendas: List<Venda>) {
        val executa = { vendaDao.insertMultiple(vendas)}
        BaseAsyncTask(executa).execute()
    }

    fun getVendaEVendedora() = vendaDao.getVendaAndVendedora()

}