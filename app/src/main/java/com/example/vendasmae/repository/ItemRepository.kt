package com.example.vendasmae.repository

import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.itens.ItemDao
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.api.ItemApi
import com.example.vendasmae.repository.banco.ItemBanco
import retrofit2.Retrofit

class ItemRepository(itemDao: ItemDao, retrofit: Retrofit) {

    val itemApi =
        ItemApi(retrofit)
    val itemBanco = ItemBanco(itemDao)

    private val liveData = itemBanco.getAll()

    fun getAll() = liveData

    fun buscarItem() {

        val quandoSucesso: (Resource<List<Produto>?>) -> Unit = {

                itemBanco.insertMultlple(it.dado!!)
        }
        val quandoFalha: (Resource<List<Produto>?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        itemApi.getAll(quandoSucesso, quandoFalha)
    }

    fun insere(vendedora: Produto){
        val quandoSucesso: (Resource<Produto?>) -> Unit = {
            itemBanco.insert(it.dado!!)
        }
        val quandoFalha: (Resource<Produto?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        itemApi.insere(vendedora, quandoSucesso, quandoFalha)

    }

    fun removeAll(){
        itemBanco.removeAll()
    }

    fun getItemVendedora(id: Long) = itemBanco.getItemVendedora(id)


    fun update(produto: Produto) {
        val quandoSucesso: (Resource<Produto?>) -> Unit = {
            itemBanco.insert(it.dado!!)
        }
        val quandoFalha: (Resource<Produto?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        itemApi.atualiza(produto, quandoSucesso, quandoFalha)
    }

    fun getProdutoEComQuemEsta() = itemBanco.getItemVendedora()

    fun getItemMaleta(id: Long) = itemBanco.getItemMaleta(id)


}