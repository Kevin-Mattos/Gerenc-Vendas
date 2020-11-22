package com.example.vendasmae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.vendasmae.banco.MainDataBase
import com.example.vendasmae.databinding.ActivityMainBinding
import com.example.vendasmae.repository.ItemRepository
import com.example.vendasmae.repository.TipoRepository
import com.example.vendasmae.repository.VendaRepository
import com.example.vendasmae.repository.VendedorasRepository
import com.example.vendasmae.view.fragment.MainFrag
import com.example.vendasmae.view.activity.transacaoFragment
import com.example.vendasmae.view.fragment.TipoFragment
import com.example.vendasmae.view.fragment.VendaFragment
import com.example.vendasmae.view.fragment.VendedoraFragment
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        val baseURL = "http://192.168.0.104:3000"

    }
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

        mBinding.navigation.visibility = View.VISIBLE

        //
        val vendedoraDao = MainDataBase.getInstance(applicationContext).vendedoraDao()
        val itemDao = MainDataBase.getInstance(applicationContext).itemDao()
        val vendaDao = MainDataBase.getInstance(applicationContext).vendaDao()
        val tipoDao = MainDataBase.getInstance(applicationContext).tipoDao()

        val vendedorasRepo = VendedorasRepository(vendedoraDao, retrofit)
        val itemRepo = ItemRepository(itemDao, retrofit)
        val vendaRepo = VendaRepository(vendaDao, retrofit)
        val tipoRepo = TipoRepository(tipoDao, retrofit)
        //
        vendaRepo.removeAll()
        itemRepo.removeAll()
        vendedorasRepo.removeAll()




        vendedorasRepo.getAll().observe(this, Observer {
            it.forEach{vendedora ->
                Log.d("vendedora", " $vendedora")
            }
        })

        vendaRepo.getAllVendas().observe(this, Observer {
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
        tipoRepo.buscarTipos()



        setNav()

        startMainFrag()
    }

    fun startMainFrag(){
        val mainFrag = MainFrag()
        title = "Principal"
        transacaoFragment {
            replace(R.id.frag_container, mainFrag)
        }

    }
    fun startVendasFrag(){
        title = "Vendas"
        val frag = VendaFragment()
        transacaoFragment {
            replace(R.id.frag_container, frag)
        }

    }
    fun startVendedorasFrag(){
        title = "Vendedoras"
        val mainFrag = VendedoraFragment()
        transacaoFragment {
            replace(R.id.frag_container, mainFrag)
        }

    }
    fun startProdutosFrag(){
        title = "Produtos"
        val frag = TipoFragment()
        transacaoFragment {
            replace(R.id.frag_container, frag)
        }
    }

    fun setNav(){
        mBinding.navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_produtos -> {
                    startProdutosFrag()
                    // Respond to navigation item 1 click
                    Log.d("selected", "produtos")
                    true
                }
                R.id.nav_venda -> {
                    startVendasFrag()
                    Log.d("selected", "nav_venda")
                    // Respond to navigation item 2 click
                    true
                }
                R.id.nav_vendedores -> {
                    startVendedorasFrag()
                    Log.d("selected", "nav_vendedores")
                    true
                }
                R.id.nav_main -> {
                    startMainFrag()

                    Log.d("selected", "produtos")
                    true
                }
                else -> false
            }
        }

        mBinding.navigation.setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.nav_produtos -> {
                    // Respond to navigation item 1 click
                    Log.d("reselected", "produtos")
                    true
                }
                R.id.nav_venda -> {
                    Log.d("reselected", "nav_venda")
                    // Respond to navigation item 2 click
                    true
                }
                R.id.nav_vendedores -> {

                    Log.d("reselected", "nav_vendedores")
                    true
                }
                R.id.nav_main -> {
                    Log.d("reselected", "nav_main")
                    true
                }
                else -> false
            }
        }


    }



}