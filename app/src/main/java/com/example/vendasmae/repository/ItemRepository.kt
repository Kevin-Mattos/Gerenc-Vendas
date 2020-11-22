package com.example.vendasmae.repository

import com.example.vendasmae.banco.itens.Item
import com.example.vendasmae.banco.itens.ItemDao
import com.example.vendasmae.banco.vendedoras.Vendedora
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.api.ItemApi
import com.example.vendasmae.repository.api.VendedoraApi
import com.example.vendasmae.repository.banco.ItemBanco
import com.example.vendasmae.repository.banco.VendedoraBanco
import retrofit2.Retrofit

class ItemRepository(itemDao: ItemDao, retrofit: Retrofit) {

    val itemApi =
        ItemApi(retrofit)
    val itemBanco = ItemBanco(itemDao)

    private val liveData = itemBanco.getAll()

    fun getAll() = liveData

    fun buscarItem() {

        val quandoSucesso: (Resource<List<Item>?>) -> Unit = {

                itemBanco.insertMultlple(it.dado!!)
        }
        val quandoFalha: (Resource<List<Item>?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        itemApi.getAll(quandoSucesso, quandoFalha)
    }

    fun insere(vendedora: Item){
        val quandoSucesso: (Resource<Item?>) -> Unit = {
            itemBanco.insert(it.dado!!)
        }
        val quandoFalha: (Resource<Item?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        itemApi.insere(vendedora, quandoSucesso, quandoFalha)

    }

    fun removeAll(){
        itemBanco.removeAll()
    }




}