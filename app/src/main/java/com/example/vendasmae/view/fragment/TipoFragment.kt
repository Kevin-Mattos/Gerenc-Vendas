package com.example.vendasmae.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vendasmae.databinding.FragmentTipoBinding
import com.example.vendasmae.view.viewmodel.TipoFragmentViewModel
import com.example.vendasmae.view.adapter.TipoAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class TipoFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    private val adapter by lazy{
        context?.let {
            TipoAdapter(context = it)
        } ?: throw IllegalArgumentException("contexto invalido")

    }

    private val mViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(
            TipoFragmentViewModel::class.java)
    }

    lateinit var mBinding: FragmentTipoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }


    }


    fun setupAdapter(){
        mBinding.prodRecyclerView.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentTipoBinding.inflate(inflater, container, false)


        setupAdapter()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        mViewModel.getTipoQuantidadeValor().observe(this, Observer {
            adapter.atualiza(it)
        })
//        tipoRepo.getAll().observe(this, Observer {
//            adapter.atualiza(it)
//        })


    }

}