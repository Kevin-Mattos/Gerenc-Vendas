package com.example.vendasmae.repository.banco

import androidx.lifecycle.LiveData
import com.example.vendasmae.banco.tipos.Tipo
import com.example.vendasmae.banco.tipos.TipoDao
import com.example.vendasmae.banco.tipos.TipoQuantidadeValor
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

    fun getTipoQuantidadeValor(): LiveData<List<TipoQuantidadeValor>> = tipoDao.getTipoQuantidadeValor()


}