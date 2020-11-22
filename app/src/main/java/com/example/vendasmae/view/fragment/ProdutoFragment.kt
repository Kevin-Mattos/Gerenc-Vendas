package com.example.vendasmae.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.databinding.FragmentProdutoBinding
import com.example.vendasmae.entities.itens.Item
import com.example.vendasmae.view.adapter.ProdutoAdapter
import com.example.vendasmae.view.viewmodel.ProdutoFragmentViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class ProdutoFragment : BaseFragment(), ProdutoAdapter.ProdutoActions {
    // TODO: Rename and change types of parameters
    private var idProduto: Long? = null

    companion object{
        const val idTipoProduto = "id"
        private const val ARG_PARAM2 = "param2"
    }

    private val adapter by lazy{
        context?.let {
            ProdutoAdapter(it, this)
        } ?: throw IllegalArgumentException("contexto invalido")

    }

    private val TAG = "ProdutoFragment"

    lateinit var mBinding: FragmentProdutoBinding

    private val mViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(ProdutoFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idProduto = it.getLong(idTipoProduto)
        }
    }

    private fun setupAdapter() {
        mBinding.produtosRecyclerView.adapter = adapter
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentProdutoBinding.inflate(inflater, container, false)
        setupAdapter()


        mViewModel.getItemVendedora(idProduto!!).observe(this, androidx.lifecycle.Observer {
            adapter.atualiza(it)
        })


        return mBinding.root
    }


    override fun adiciona() {
        Log.d(TAG, "adicionando")
        mViewModel.insere(Item(0, "carrinho", 236f, null, 1, -1))

    }

    override fun onTipoClick(id: Long) {
        Log.d(TAG, "produto clicado")
    }

}