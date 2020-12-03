package com.example.vendasmae.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vendasmae.MainActivity
import com.example.vendasmae.R
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.databinding.FragmentTipoBinding
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.view.viewmodel.TipoFragmentViewModel
import com.example.vendasmae.view.adapter.TipoAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class TipoFragment : BaseFragment(), TipoAdapter.TipoActions {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    val TAG = "TipoFragment"


    val mMainActivity by lazy {
        activity as MainActivity
    }


    private val adapter by lazy{
        context?.let {
            TipoAdapter(it, this)
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
            it?.let {
                adapter.atualiza(it)
            }
        })
//        tipoRepo.getAll().observe(this, Observer {
//            adapter.atualiza(it)
//        })


    }

    override fun onTipoClick(id: Long) {
        ///Toast.makeText(context, "eae", Toast.LENGTH_LONG).show()
        mMainActivity.startProdutoFrag(idTipo = id)
    }

    override fun updateTipo(tipo: Tipo) {
        showDialog(tipo)
    }

    override fun adiciona() {
        Log.d(TAG, "adicionando")
       // mViewModel.insere(Tipo(0, "TIPO ${mViewModel.getQuantidade()}"))
        showDialog()
    }


    private fun showDialog(tipo: Tipo? = null) {

        val builder = AlertDialog.Builder(this.context)
        // Get the layout inflater
        val inflater = requireActivity().layoutInflater;

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.custom_dialog_adiciona_tipo, null))
        builder.setPositiveButton("Criar"){ dialog, widht ->


        }

        builder.setNegativeButton("sair"){ dialog, widht ->

            dialog.dismiss()
        }


        val dialog = builder.create()
        dialog.show()

        tipo?.let {

            dialog.findViewById<EditText>(R.id.dialog_tipo_nome).setText(it.nome)
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText("Remover")
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener {
                mViewModel.remove(tipo)
                dialog.dismiss()
            }
        }

        val theButton: Button = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        theButton.setOnClickListener {
            val nome = dialog.findViewById<EditText>(R.id.dialog_tipo_nome).text.toString()
            if(tipo == null)
                mViewModel.insere(Tipo(0, if(nome.isNotBlank()) nome else "Escreva um nome${mViewModel.getQuantidade()}"))
            else{
                tipo.nome = nome
                mViewModel.update(tipo)
            }
            dialog.dismiss()
        }



    }

}