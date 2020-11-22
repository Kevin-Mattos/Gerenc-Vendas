package com.example.vendasmae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.databinding.ActivityMainBinding
import com.example.vendasmae.repository.ItemRepository
import com.example.vendasmae.repository.TipoRepository
import com.example.vendasmae.repository.VendaRepository
import com.example.vendasmae.repository.VendedorasRepository
import com.example.vendasmae.view.activity.extension.transacaoFragment
import com.example.vendasmae.view.fragment.*
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        val baseURL = "http://192.168.0.104:3000" //192.168.0.114
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

    lateinit var currentFrag: BaseFragment

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

        //TODO SINCRONIZAR EM VEZ DE REMOVER
        vendaRepo.removeAll()
        itemRepo.removeAll()
        vendedorasRepo.removeAll()
        tipoRepo.removeAll()




        vendedorasRepo.getVendedoraValorQuantidade().observe(this, Observer {
            it.forEach{vendedora ->
                Log.d("VENDEDORA: ", " ${vendedora.vendedora}")
            }
        })

        vendaRepo.getAllVendas().observe(this, Observer {
            it.forEach{venda ->
                Log.d("VENDA: ", " ${venda.data}")
            }
        })

        itemRepo.getAll().observe(this, Observer {
            it.forEach{item ->
                Log.d("ITEM: ", " ${item.nome}")
            }
        })

        tipoRepo.getAll().observe(this, Observer {
            it.forEach{item ->
                Log.d("TIPO: ", " ${item.nome}")
            }
        })


        vendedorasRepo.buscarVendedoras()
        itemRepo.buscarItem()
        vendaRepo.buscarVendas()
        tipoRepo.buscarTipos()


        setNav()
        startMainFrag()


    }

    private fun setFAB() {
        mBinding.addFloatingActionButton.show()
        mBinding.addFloatingActionButton.setOnClickListener{
            currentFrag.adiciona()
        }
    }

    fun startMainFrag(){
        currentFrag = MainFrag()
        title = "Principal"
        transacaoFragment {
            replace(R.id.frag_container, currentFrag)
        }
        setFAB()

    }
    fun startVendasFrag(){
        title = "Vendas"
        currentFrag = VendaFragment()
        transacaoFragment {
            replace(R.id.frag_container, currentFrag)
        }
        setFAB()
    }
    fun startVendedorasFrag(){
        title = "Vendedoras"
        currentFrag = VendedoraFragment()
        transacaoFragment {
            replace(R.id.frag_container, currentFrag)
        }
        setFAB()
    }
    fun startTipoFrag(){
        title = "Produtos"
        currentFrag = TipoFragment()
        transacaoFragment {
            replace(R.id.frag_container, currentFrag)
        }
        setFAB()
    }

    fun startProdutoFrag(id: Long) {
        title = "Produtos"
        currentFrag = ProdutoFragment()
        currentFrag.arguments = Bundle()
        currentFrag.arguments?.putLong(ProdutoFragment.idTipoProduto, id)
        transacaoFragment {
            replace(R.id.frag_container, currentFrag)
            this.addToBackStack(null)
        }
        setFAB()
        //hideFab()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStackImmediate()

    }

    private fun hideFab() {
        mBinding.addFloatingActionButton.hide()
    }

    fun setNav(){
        mBinding.navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_produtos -> {
                    startTipoFrag()
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