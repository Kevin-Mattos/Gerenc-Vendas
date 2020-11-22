package com.example.vendasmae.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.databinding.FragmentVendaBinding
import com.example.vendasmae.entities.vendas.Venda
import com.example.vendasmae.view.adapter.VendaAdapter
import com.example.vendasmae.view.viewmodel.VendaFragmentViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VendaFragment : BaseFragment() {


    val TAG = "VendaFragment"

    private val adapter by lazy{
        context?.let {
        VendaAdapter(context = it)
    } ?: throw IllegalArgumentException("contexto invalido")

}


    lateinit var mBinding: FragmentVendaBinding// by lazy { FragmentMainBinding.inflate(layoutInflater)}


    private val mViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(
            VendaFragmentViewModel::class.java)
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

        mViewModel.getVendasEVendedoras().observe(this, Observer { lista ->
            lista?.let {
                adapter.atualiza(it)
            }

        })
    }

    override fun adiciona() {
        Log.d(TAG, "Adicionando venda")
        val qtd = mViewModel.getQuantidade()?: 2
        mViewModel.insere(Venda(0, qtd*23.6.toFloat() ,0f,"data", 3, 4))

    }

}