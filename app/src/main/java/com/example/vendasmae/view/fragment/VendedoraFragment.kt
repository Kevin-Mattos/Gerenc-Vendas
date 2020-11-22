package com.example.vendasmae.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.vendasmae.MainActivity
import com.example.vendasmae.banco.MainDataBase
import com.example.vendasmae.databinding.FragmentVendedoraBinding
import com.example.vendasmae.repository.VendedorasRepository
import com.example.vendasmae.view.adapter.VendedorasAdapter
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VendedoraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VendedoraFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val adapter by lazy{
        context?.let {
            VendedorasAdapter(context = it)
        } ?: throw IllegalArgumentException("contexto invalido")

    }

    lateinit var mBinding: FragmentVendedoraBinding

    val retrofit by lazy{

        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(MainActivity.baseURL)
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

    fun setupAdapter(){
        mBinding.vendedoraRecyclerView.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentVendedoraBinding.inflate(inflater, container, false)


        setupAdapter()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemDao = MainDataBase.getInstance(activity!!.applicationContext).vendedoraDao()


        val vendedoraRepo = VendedorasRepository(itemDao, retrofit)


                vendedoraRepo.getVendedoraValorQuantidade().observe(this, Observer { vend ->
                    adapter.atualiza(vend)
                })


//        vendedoraRepo.getAll().observe(this, Observer {vendedoras ->
//            vendedoras.forEach()
//
//        })




    }
}