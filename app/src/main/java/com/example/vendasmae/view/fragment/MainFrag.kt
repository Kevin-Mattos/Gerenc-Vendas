package com.example.vendasmae.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vendasmae.MainActivity.Companion.baseURL
import com.example.vendasmae.banco.itens.ItemDatabase
import com.example.vendasmae.banco.vendas.VendaDatabase
import com.example.vendasmae.banco.vendedoras.VendedoraDatabase
import com.example.vendasmae.databinding.FragmentMainBinding
import com.example.vendasmae.repository.ItemRepository
import com.example.vendasmae.repository.VendaRepository
import com.example.vendasmae.repository.VendedorasRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFrag : Fragment() {
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
        val vendedoraDao = VendedoraDatabase.getInstance(activity!!.applicationContext).vendedoraDao()
        VendedorasRepository(vendedoraDao, retrofit)
    }
    val itemRepo by lazy{
        val itemDao = ItemDatabase.getInstance(activity!!.applicationContext).itemDao()
        ItemRepository(itemDao, retrofit)
    }
    val vendaRepo by lazy{
        val vendaDao = VendaDatabase.getInstance(activity!!.applicationContext).vendaDao()
        VendaRepository(vendaDao, retrofit)
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




        mBinding.item.setOnClickListener {
            itemRepo.buscarItem()
        }

        mBinding.vendas.setOnClickListener {
            vendaRepo.buscarVendas()
        }

        Log.d(TAG, "setando clisks")

        mBinding.vendedora.setOnClickListener {
            vendedorasRepo.buscarVendedoras()

        }
    }

    override fun onResume() {
        super.onResume()
        itemRepo.buscarItem()
        vendaRepo.buscarVendas()
        vendedorasRepo.buscarVendedoras()

        Log.d(TAG, "onResume")
    }


}