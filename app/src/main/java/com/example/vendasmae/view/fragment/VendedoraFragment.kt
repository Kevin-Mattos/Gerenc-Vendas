package com.example.vendasmae.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vendasmae.R
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.databinding.FragmentVendedoraBinding
import com.example.vendasmae.entities.vendedoras.Vendedora
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
class VendedoraFragment : BaseFragment(), VendedorasAdapter.VendedoraActions {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val TAG = "VendedoraFragment"

    private val adapter by lazy {
        context?.let {
            VendedorasAdapter(context = it, actions = this)
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

        setHasOptionsMenu(true)
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

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_action_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.actionMaiorQuantidadeVenda -> {
                mViewModel.getVendedoraValorQuantidade().removeObservers(this)

                mViewModel.getVendedoraValorQuantidadeOrderByQuantidadeVendas().observe(this, Observer {
                    adapter.atualiza(it)
                })
                true
            }
            R.id.actionMaiorValor -> {
                mViewModel.getVendedoraValorQuantidade().removeObservers(this)

                mViewModel.getVendedoraValorQuantidadeOrderByValorVendido().observe(this, Observer {
                    adapter.atualiza(it)
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    fun setupAdapter() {
        mBinding.vendedoraRecyclerView.adapter = adapter
    }


    override fun adiciona(){
        Log.d(TAG, "adiciona")
        //mViewModel.insere(Vendedora(0, "ADICIONEI ${mViewModel.getQTD()}"))
        showDialog()
    }


    private fun showDialog(vendedora: Vendedora? = null) {

        val builder = AlertDialog.Builder(this.context)
        // Get the layout inflater
        val inflater = requireActivity().layoutInflater;

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.custom_dialog_adiciona_vendedora, null))
        builder.setPositiveButton("Criar"){ dialog, widht ->


        }

        builder.setNegativeButton("sair"){ dialog, widht ->

            dialog.dismiss()
        }


        val dialog = builder.create()
        dialog.show()

        vendedora?.let {
            dialog.findViewById<TextView>(R.id.dialog_vendedora_title).setText("Atualizar Vendedora")
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText("Remover")
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener {
                mViewModel.removeVendedora(vendedora)
                dialog.dismiss()
            }
        }

        val theButton: Button = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        theButton.setOnClickListener {
            val nome = dialog.findViewById<EditText>(R.id.dialog_vendedora_nome).text.toString()

            if(vendedora == null)
                mViewModel.insere(Vendedora(0, if(nome.isNotBlank()) nome else "Escreva um nome${mViewModel.getQTD()}"))
            else{
                vendedora.nome = nome
                mViewModel.updateVendedora(vendedora)
            }
            dialog.dismiss()
        }



    }

    override fun longClickVendedora(vendedora: Vendedora) {
        showDialog(vendedora)
    }

    override fun clickVendedora(vendedora: Vendedora) {

    }


}