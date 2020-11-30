package com.example.vendasmae.repository.banco

import com.example.vendasmae.baseClass.BaseAsyncTask
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.maleta.MaletaDao

class MaletaBanco (val tipoDao: MaletaDao) {

    fun getAll() = tipoDao.getAll()

    fun insert(vendedora: Maleta){
        val executa = { tipoDao.insertMaleta(vendedora)}
        BaseAsyncTask(executa).execute()
    }

    fun removeAll() {
        val executa = { tipoDao.removeAll()}
        BaseAsyncTask(executa).execute()
    }

    fun insertMultlple(vendas: List<Maleta>) {
        val executa = { tipoDao.insertMultiple(vendas)}
        BaseAsyncTask(executa).execute()
    }

    fun getMaletaQuantidadeValor() = tipoDao.getMaletaQuantidadeValor()


}