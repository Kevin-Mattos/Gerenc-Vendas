package com.example.vendasmae.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.vendasmae.MainActivity
import com.example.vendasmae.banco.MainDataBase
import com.example.vendasmae.databinding.FragmentVendaBinding
import com.example.vendasmae.repository.VendaRepository
import com.example.vendasmae.repository.VendedorasRepository
import com.example.vendasmae.view.adapter.VendaAdapter
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_venda.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VendaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VendaFragment : Fragment() {



    private val adapter by lazy{
        context?.let {
        VendaAdapter(context = it)
    } ?: throw IllegalArgumentException("contexto invalido")

}

    lateinit var mBinding: FragmentVendaBinding// by lazy { FragmentMainBinding.inflate(layoutInflater)}

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

        }


    }


    fun setupAdapter(){
        mBinding.vendaRecyclerView.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentVendaBinding.inflate(inflater, container, false)


        setupAdapter()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemDao = MainDataBase.getInstance(activity!!.applicationContext).vendaDao()


        val vendaRepo = VendaRepository(itemDao, retrofit)


        vendaRepo.getVendasEVendedoras().observe(this, Observer { lista ->

            lista?.let {
                adapter.atualiza(it)
            }

        })


    }
}