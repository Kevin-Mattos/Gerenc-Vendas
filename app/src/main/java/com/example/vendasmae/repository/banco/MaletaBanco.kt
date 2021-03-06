package com.example.vendasmae.repository.banco

import com.example.vendasmae.baseClass.BaseAsyncTask
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.maleta.MaletaDao
import com.example.vendasmae.entities.vendedoras.Vendedora

class MaletaBanco (val maletaDao: MaletaDao) {

    fun getAll() = maletaDao.getAll()

    fun insert(maleta: Maleta){
        val executa = { maletaDao.insertMaleta(maleta)}
        BaseAsyncTask(executa).execute()
    }

    fun removeAll() {
        val executa = { maletaDao.removeAll()}
        BaseAsyncTask(executa).execute()
    }

    fun insertMultlple(maletas: List<Maleta>) {
        val executa = { maletaDao.insertMultiple(maletas)}
        BaseAsyncTask(executa).execute()
    }

    fun getMaletaQuantidadeValor() = maletaDao.getMaletaQuantidadeValor()

    fun removeById(id: Long) {
        val executa = { maletaDao.removeById(id)}
        BaseAsyncTask(executa).execute()
    }


    fun updateMaleta(maleta: Maleta) {
        val executa = { maletaDao.updateMaleta(maleta)}
        BaseAsyncTask(executa).execute()
    }

}