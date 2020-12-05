package com.example.vendasmae.repository.banco

import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.tipos.TipoDao
import com.example.vendasmae.baseClass.BaseAsyncTask

class TipoBanco  (val tipoDao: TipoDao) {

    fun getAll() = tipoDao.getAll()

    fun insert(vendedora: Tipo){
        val executa = { tipoDao.insertTipo(vendedora)}
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

    fun getTipoQuantidadeValor() = tipoDao.getTipoQuantidadeValor()

    fun remove(id: Long) {
        val executa = { tipoDao.removeById(id)}
        BaseAsyncTask(executa).execute()
    }


    fun updateTipo(tipo: Tipo) {
        val executa = { tipoDao.updateTipo(tipo)}
        BaseAsyncTask(executa).execute()
    }


}