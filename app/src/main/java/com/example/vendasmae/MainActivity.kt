package com.example.vendasmae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.lifecycle.Observer
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.databinding.ActivityMainBinding
import com.example.vendasmae.repository.*
import com.example.vendasmae.view.activity.extension.transacaoFragment
import com.example.vendasmae.view.fragment.*
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        val baseURL = "http://192.168.0.104:3000" //192.168.0.114 104
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

       val vendedoraDao = MainDataBase.getInstance(applicationContext).vendedoraDao()
        val itemDao = MainDataBase.getInstance(applicationContext).produtoDao()
        val vendaDao = MainDataBase.getInstance(applicationContext).vendaDao()
        val tipoDao = MainDataBase.getInstance(applicationContext).tipoDao()
        val maletaDao = MainDataBase.getInstance(applicationContext).maletaDao()


        val vendedorasRepo = VendedorasRepository(vendedoraDao, retrofit)
        val itemRepo = ProdutoRepository(itemDao, retrofit)
        val vendaRepo = VendaRepository(vendaDao, retrofit)
        val tipoRepo = TipoRepository(tipoDao, retrofit)
        val maletaRepo = MaletaRepository(maletaDao, retrofit)

        //TODO SINCRONIZAR EM VEZ DE REMOVER
        vendaRepo.removeAll()
        itemRepo.removeAll()
        vendedorasRepo.removeAll()
        tipoRepo.removeAll()
        maletaRepo.removeAll()




        vendedorasRepo.getVendedoraValorQuantidade().observe(this, Observer {
            it.forEach{vendedora ->
                Log.d("VENDEDORA: ", " ${vendedora.vendedora}")
            }
        })

        vendaRepo.getAllVendas().observe(this, Observer {
            it.forEach{venda ->
                Log.d("VENDAnj: ", " ${venda.data}")
            }
        })

        itemRepo.getAll().observe(this, Observer {
            it.forEach{item ->
                Log.d("ITEM: ", " ${item.nome}")
            }
        })

        tipoRepo.getAll().observe(this, Observer {
            it?.forEach{item ->
                Log.d("TIPO: ", " ${item.nome}")
            }
        })

        maletaRepo.getAll().observe(this, Observer {
            it?.forEach{item ->
                Log.d("TIPO: ", " ${item.nome}")
            }
        })


//        vendedorasRepo.buscarVendedoras()
//        itemRepo.buscarItem()
//        vendaRepo.buscarVendas()
//        tipoRepo.buscarTipos()


        setNav()
        startMainFrag()
        mBinding.navigation.selectedItemId = R.id.nav_main


    }

    private fun setFAB() {
        if(currentFrag is MainFrag)
            hideFab()
        else
            showFab()

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
        title = "Tipo"
        currentFrag = TipoFragment()
        transacaoFragment {
            replace(R.id.frag_container, currentFrag, tipoTag)
            //this.addToBackStack(tipoTag)
        }
        setFAB()
    }

    fun startMaletaFrag(){
        title = "Maleta"
        currentFrag = MaletaFragment()
        transacaoFragment {
            replace(R.id.frag_container, currentFrag, maletaTag)
            //this.addToBackStack(tipoTag)
        }
        setFAB()
    }

    val prodTag = "produtoTag"
    val tipoTag = "tipoTag"
    val maletaTag = "maletaTag"
    fun startProdutoFrag(idTipo: Long? = null, idMaleta: Long? = null) {
        title = "Produtos"
        currentFrag = ProdutoFragment()
        currentFrag.arguments = Bundle()
        if(idTipo != null)
            currentFrag.arguments?.putLong(ProdutoFragment.idTipoProduto, idTipo)
        else if(idMaleta != null)
            currentFrag.arguments?.putLong(ProdutoFragment.idMaletaProduto, idMaleta)
        transacaoFragment {
            replace(R.id.frag_container, currentFrag, prodTag)
            this.addToBackStack(null)
        }
        setFAB()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val actualFrag = supportFragmentManager.findFragmentByTag(tipoTag) as BaseFragment?
        supportFragmentManager.popBackStack()
        if(actualFrag != null && actualFrag is TipoFragment)
            currentFrag = actualFrag

    }

    private fun hideFab() {
        mBinding.addFloatingActionButton.hide()
    }

    fun showFab(){
        mBinding.addFloatingActionButton.show()
    }

    fun setNav(){
        mBinding.navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_produtos -> {
                    startTipoFrag()
                    // Respond to navigation item 1 click
                    Log.d("selected", "tipo")
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

                    Log.d("selected", "main")
                    true
                }
                R.id.nav_maleltas -> {
                    startMaletaFrag()

                    Log.d("selected", "maleta")
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