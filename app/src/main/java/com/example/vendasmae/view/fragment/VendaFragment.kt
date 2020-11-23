package com.example.vendasmae.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vendasmae.R
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.databinding.FragmentVendaBinding
import com.example.vendasmae.entities.itens.Item
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.vendas.Venda
import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.view.adapter.VendaAdapter
import com.example.vendasmae.view.viewmodel.VendaFragmentViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VendaFragment : BaseFragment(), AdapterView.OnItemSelectedListener {


    private lateinit var vendedoraAdapter: ArrayAdapter<Vendedora>
    private lateinit var prodAdapter: ArrayAdapter<Item>
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

        mViewModel.getAllItens().observe(this, Observer {
            mViewModel.itens = it.filter { item -> !item.foiVendido() }
        })
         mViewModel.getAllVendedoras().observe(this, Observer {
             mViewModel.vendedoras = it
         })




    }

    override fun adiciona() {
        Log.d(TAG, "Adicionando venda")
        val qtd = mViewModel.getQuantidade()?: 2
        //mViewModel.insere(Venda(0, qtd*23.6.toFloat() ,0f,"data", 3, 4))
        showDialog(null, mViewModel.itens, mViewModel.vendedoras)
    }

    private fun showDialog(item: Item?, tipos: List<Item>, vendedoras: List<Vendedora>) {




        val builder = AlertDialog.Builder(this.context)
        // Get the layout inflater
        val inflater = requireActivity().layoutInflater;

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.custom_dialog_adiciona_venda, null))
        builder.setPositiveButton("Criar"){ dialog, widht ->

        }

        builder.setNegativeButton("sair"){ dialog, widht ->

            dialog.dismiss()
        }



        prodAdapter = ArrayAdapter<Item>(this.context!!, R.layout.support_simple_spinner_dropdown_item)
        prodAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        prodAdapter.addAll(tipos)

        vendedoraAdapter = ArrayAdapter<Vendedora>(this.context!!, R.layout.support_simple_spinner_dropdown_item)
        vendedoraAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        vendedoraAdapter.addAll(vendedoras)




        val dialog = builder.create()
        dialog.show()
        item?.let { item ->
            dialog.findViewById<EditText>(R.id.dialog_prod_nome).setText(item.nome)
            dialog.findViewById<EditText>(R.id.dialog_prod_modelo).setText(item.modelo)
            dialog.findViewById<EditText>(R.id.dialog_prod_valor).setText(item.valor.toString())

            mViewModel.selectedVendedora = vendedoras.first { it.id == item.id_vendedora }
            mViewModel.selectedItem = tipos.first { it.id == item.id_tipo }
        }



        val theButton: Button = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        theButton.setOnClickListener {
            val valor = dialog.findViewById<EditText>(R.id.dialog_venda_valor).text.toString()
            val desconto = dialog.findViewById<EditText>(R.id.dialog_venda_desconto).text.toString()

            //val nome = dialog.findViewById<EditText>(R.id.dialog_prod_nome).text.toString()
            //val nome = dialog.findViewById<EditText>(R.id.dialog_prod_nome).text.toString()
//            if(item != null){
//                item.nome = nome
//                item.modelo = modelo
//                item.valor = valor.toFloatOrNull()?:0f
//                item.id_vendedora = mViewModel.selectedVendedora!!.id
//                item.id_tipo = mViewModel.selectedItem!!.id
//                mViewModel.update(item)
//            }
//            else
                mViewModel.insere(Venda(1,valor.toFloatOrNull()?:46.6f,  desconto.toFloatOrNull()?:mViewModel.selectedItem?.valor?:0f,"",mViewModel.selectedItem?.id?:0, mViewModel.selectedVendedora?.id?:0))
            dialog.dismiss()
        }

        val prodSpinner = dialog.findViewById<Spinner>(R.id.dialog_venda_item_spinner)//.adapter = ada
        prodSpinner.adapter = prodAdapter
        prodSpinner.onItemSelectedListener = this

        val vendSpinner = dialog.findViewById<Spinner>(R.id.dialog_venda_vendedora_spinner)//.adapter = ada
        vendSpinner.adapter = vendedoraAdapter
        vendSpinner.onItemSelectedListener = this

        mViewModel.selectedItem?.let {
            prodSpinner.setSelection(mViewModel.getSelectedItemPos())

        }

        mViewModel.selectedVendedora?.let {
            vendSpinner.setSelection(mViewModel.getSelectedVendedoraPos())
        }


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        when(p0?.selectedItem){
            is Vendedora ->{
                mViewModel.selectedVendedora = p0.selectedItem as Vendedora


                prodAdapter.clear()
                val t = mViewModel.itens.filter { (mViewModel.selectedVendedora as Vendedora).id == it.id_vendedora }
                prodAdapter.addAll(t)
                if(t.isNotEmpty())
                    mViewModel.selectedItem = t[0]
            }
            is Item ->{
                mViewModel.selectedItem = p0.selectedItem as Item
                (p0.adapter as ArrayAdapter<Item>)
            }
        }

    }

}