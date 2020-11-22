package com.example.vendasmae.repository.banco

import com.example.vendasmae.banco.tipos.Tipo
import com.example.vendasmae.banco.tipos.TipoDao
import com.example.vendasmae.banco.vendas.Venda

class TipoBanco  (val tipoDao: TipoDao) {

    fun getAll() = tipoDao.getAll()

    fun insert(vendedora: Tipo){
        val executa = { tipoDao.insertLista(vendedora)}
        BaseAsyncTask(executa).execute()
    }

    fun removeAll() {
        val executa = { tipoDao.removeAll()}
        BaseAsyncTask(executa).execute()
    }

    fun insertMultlple(vendas: List<Tipo>) {
        val executa = { tipoDao.insertMultiple(vendas)}
        BaseAsyncTask(executa).execute()
    }

    //fun getVendaEVendedora() = tipoDao.getVendaAndVendedora()

}