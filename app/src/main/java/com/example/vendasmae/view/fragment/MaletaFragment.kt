package com.example.vendasmae.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
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
import com.example.vendasmae.databinding.FragmentMaletaBinding
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.maleta.MaletaQuantidadeValor
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.view.adapter.MaletaAdapter
import com.example.vendasmae.view.adapter.TipoAdapter
import com.example.vendasmae.view.viewmodel.MaletaFragmentViewModel
import com.example.vendasmae.view.viewmodel.TipoFragmentViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MaletaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MaletaFragment : BaseFragment(), MaletaAdapter.MaletaActions {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val TAG = "MaletaFragment"




    val mMainActivity by lazy {
        activity as MainActivity
    }


    private val adapter by lazy{
        context?.let {
            MaletaAdapter(it, this)
        } ?: throw IllegalArgumentException("contexto invalido")

    }

    private val mViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(
            MaletaFragmentViewModel::class.java)
    }

    override fun adiciona() {
        showDialog()
    }

    lateinit var  mBinding: FragmentMaletaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentMaletaBinding.inflate(inflater, container, false)
        setAdapter()

        mViewModel.getMaletaQuantidadeValor().observe(this, Observer {
            it?.let {
                adapter.atualiza(it)
            }

        })

        return mBinding.root
    }

    private fun setAdapter() {
        mBinding.maletaRecyclerView.adapter = adapter
    }


    override fun onItemClick(item: MaletaQuantidadeValor) {
        mMainActivity.startProdutoFrag(idMaleta = item.maleta?.id)
    }

    override fun updateItem(item: MaletaQuantidadeValor) {

    }

    private fun showDialog() {

        val builder = AlertDialog.Builder(this.context)
        // Get the layout inflater
        val inflater = requireActivity().layoutInflater;

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.custom_dialog_adiciona_maleta, null))
        builder.setPositiveButton("Criar"){ dialog, widht ->


        }

        builder.setNegativeButton("sair"){ dialog, widht ->

            dialog.dismiss()
        }


        val dialog = builder.create()
        dialog.show()

        val theButton: Button = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        theButton.setOnClickListener {
            val nome = dialog.findViewById<EditText>(R.id.dialog_maleta_nome).text.toString()
            if(!nome.isBlank())
                mViewModel.insere(Maleta(0, nome))
            dialog.dismiss()
        }


    }

}