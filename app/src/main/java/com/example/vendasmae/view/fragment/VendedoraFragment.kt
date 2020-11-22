package com.example.vendasmae.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vendasmae.databinding.FragmentVendedoraBinding
import com.example.vendasmae.view.adapter.VendedorasAdapter
import com.example.vendasmae.view.viewmodel.VendedoraFragmentViewModel

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

    private val adapter by lazy {
        context?.let {
            VendedorasAdapter(context = it)
        } ?: throw IllegalArgumentException("contexto invalido")

    }

    lateinit var mBinding: FragmentVendedoraBinding

    private val mViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(
            VendedoraFragmentViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun setupAdapter() {
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


        mViewModel.getVendedoraValorQuantidade().observe(this, Observer { vend ->
            adapter.atualiza(vend)
        })


//        vendedoraRepo.getAll().observe(this, Observer {vendedoras ->
//            vendedoras.forEach()
//
//        })


    }
}