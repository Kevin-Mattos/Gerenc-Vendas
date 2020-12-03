package com.example.vendasmae.repository.banco

import com.example.vendasmae.baseClass.BaseAsyncTask
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.maleta.MaletaDao

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


}