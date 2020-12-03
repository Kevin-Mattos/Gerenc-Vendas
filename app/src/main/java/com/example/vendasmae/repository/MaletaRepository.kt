package com.example.vendasmae.repository

import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.maleta.MaletaDao
import com.example.vendasmae.repository.api.MaletaApi
import com.example.vendasmae.repository.banco.MaletaBanco
import retrofit2.Retrofit

class MaletaRepository(itemDao: MaletaDao, retrofit: Retrofit) {
    val maletaApi =
        MaletaApi(retrofit)
    val maletaBanco = MaletaBanco(itemDao)

    private val liveData = maletaBanco.getAll()

    fun getAll() = liveData

    fun buscarMaletas() {

        val quandoSucesso: (Resource<List<Maleta>?>) -> Unit = {

            maletaBanco.insertMultlple(it.dado!!)
        }
        val quandoFalha: (Resource<List<Maleta>?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        maletaApi.getAll(quandoSucesso, quandoFalha)
    }

    fun insere(vendedora: Maleta){
        val quandoSucesso: (Resource<Maleta?>) -> Unit = {
            maletaBanco.insert(it.dado!!)
        }
        val quandoFalha: (Resource<Maleta?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        maletaApi.insere(vendedora, quandoSucesso, quandoFalha)

    }

    fun removeAll(){
        maletaBanco.removeAll()
    }

    fun update(item: Maleta) {
        val quandoSucesso: (Resource<Maleta?>) -> Unit = {
            maletaBanco.insert(it.dado!!)
        }
        val quandoFalha: (Resource<Maleta?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        maletaApi.atualiza(item, quandoSucesso, quandoFalha)
    }



    fun getMaletaQuantidadeValor() = maletaBanco.getMaletaQuantidadeValor()



}