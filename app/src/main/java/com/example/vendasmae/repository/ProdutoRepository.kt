package com.example.vendasmae.repository

import android.util.Log
import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.itens.ProdutoDao
import com.example.vendasmae.baseClass.Resource
import com.example.vendasmae.repository.api.ProdutoApi
import com.example.vendasmae.repository.banco.ProdutoBanco
import retrofit2.Retrofit

class ProdutoRepository(produtoDao: ProdutoDao, retrofit: Retrofit) {

    val produtoApi =
        ProdutoApi(retrofit)
    val produtoBanco = ProdutoBanco(produtoDao)

    private val liveData = produtoBanco.getAll()

    fun getAll() = liveData

    fun buscarProduto() {

        val quandoSucesso: (Resource<List<Produto>?>) -> Unit = {

                produtoBanco.insertMultlple(it.dado!!)
        }
        val quandoFalha: (Resource<List<Produto>?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        produtoApi.getAll(quandoSucesso, quandoFalha)
    }

    fun insere(produto: Produto){
        val quandoSucesso: (Resource<Produto?>) -> Unit = {
            produtoBanco.insert(it.dado!!)
        }
        val quandoFalha: (Resource<Produto?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
            Log.d("", "façlho")
        }

        produtoApi.insere(produto, quandoSucesso, quandoFalha)

    }

    fun removeAll(){
        produtoBanco.removeAll()
    }

    fun getProdutoVendedora(id: Long) = produtoBanco.getProdutoVendedora(id)


    fun update(produto: Produto) {
        val quandoSucesso: (Resource<Produto?>) -> Unit = {
            produtoBanco.insert(it.dado!!)
        }
        val quandoFalha: (Resource<Produto?>) -> Unit = {
//            liveData.value?.erro = "Falha ao se comunicar"
        }

        produtoApi.atualiza(produto, quandoSucesso, quandoFalha)
    }

    fun getProdutoEComQuemEsta() = produtoBanco.getProdutoVendedora()

    fun getProdutoMaleta(id: Long) = produtoBanco.getProdutoMaleta(id)


}