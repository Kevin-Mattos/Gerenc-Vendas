package com.example.vendasmae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.vendasmae.banco.itens.ItemDatabase
import com.example.vendasmae.banco.vendas.VendaDatabase
import com.example.vendasmae.banco.vendedoras.VendedoraDatabase
import com.example.vendasmae.databinding.ActivityMainBinding
import com.example.vendasmae.repository.ItemRepository
import com.example.vendasmae.repository.VendaRepository
import com.example.vendasmae.repository.VendedorasRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    val baseURL = "http://192.168.0.104:3000"

    val retrofit by lazy{

        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    val mBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        val vendedoraDao = VendedoraDatabase.getInstance(applicationContext).vendedoraDao()
        val itemDao = ItemDatabase.getInstance(applicationContext).itemDao()
        val vendaDao = VendaDatabase.getInstance(applicationContext).vendaDao()

        val vendedorasRepo = VendedorasRepository(vendedoraDao, retrofit)
        val itemRepo = ItemRepository(itemDao, retrofit)
        val vendaRepo = VendaRepository(vendaDao, retrofit)

        vendedorasRepo.getAll().observe(this, Observer {
            it.forEach{vendedora ->
                Log.d("vendedora", " $vendedora")
            }
        })

        vendaRepo.getAll().observe(this, Observer {
            it.forEach{venda ->
                Log.d("venda", " ${venda.data}")
            }
        })

        itemRepo.getAll().observe(this, Observer {
            it.forEach{item ->
                Log.d("item", " ${item.nome}")
            }
        })

        vendedorasRepo.buscarVendedoras()
        itemRepo.buscarItem()
        vendaRepo.buscarVendas()


        mBinding.item.setOnClickListener {
            itemRepo.buscarItem()
        }

        mBinding.vendas.setOnClickListener {
            vendaRepo.buscarVendas()
        }

        Log.d("TAG", "stado clisks")

        mBinding.vendedora.setOnClickListener {
            vendedorasRepo.buscarVendedoras()

        }

    }
}