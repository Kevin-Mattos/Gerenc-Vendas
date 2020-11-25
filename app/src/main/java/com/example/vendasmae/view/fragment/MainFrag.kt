package com.example.vendasmae.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vendasmae.MainActivity.Companion.baseURL
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.entities.MainDataBase
import com.example.vendasmae.databinding.FragmentMainBinding
import com.example.vendasmae.repository.*
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFrag : BaseFragment() {
    private val TAG = "mainFrag"

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var mBinding: FragmentMainBinding// by lazy { FragmentMainBinding.inflate(layoutInflater)}

    val retrofit by lazy{

        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    val vendedorasRepo by lazy{
        val vendedoraDao = MainDataBase.getInstance(activity!!.applicationContext).vendedoraDao()
        VendedorasRepository(vendedoraDao, retrofit)
    }
    val itemRepo by lazy{
        val itemDao = MainDataBase.getInstance(activity!!.applicationContext).itemDao()
        ItemRepository(itemDao, retrofit)
    }
    val vendaRepo by lazy{
        val vendaDao = MainDataBase.getInstance(activity!!.applicationContext).vendaDao()
        VendaRepository(vendaDao, retrofit)
    }

    val tipoRepo by lazy{
        val tipoDao = MainDataBase.getInstance(activity!!.applicationContext).tipoDao()
        TipoRepository(tipoDao, retrofit)
    }

    val maletaRepo by lazy{
        val maletaDao = MainDataBase.getInstance(activity!!.applicationContext).maletaDao()
        MaletaRepository(maletaDao, retrofit)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mBinding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.getItem.setOnClickListener {
            itemRepo.buscarItem()
        }

        mBinding.getVendas.setOnClickListener {
            vendaRepo.buscarVendas()
        }

        Log.d(TAG, "setando clisks")

        mBinding.getVendedora.setOnClickListener {
            vendedorasRepo.buscarVendedoras()

        }
        mBinding.getTipo.setOnClickListener {
            tipoRepo.buscarTipos()

        }

    }

    override fun onResume() {
        super.onResume()
        itemRepo.buscarItem()
        vendaRepo.buscarVendas()
        vendedorasRepo.buscarVendedoras()
        tipoRepo.buscarTipos()
        maletaRepo.buscarMaletas()

        Log.d(TAG, "onResume")
    }

    override fun adiciona() {
        Log.d("add", "notImplemented")
    }


}