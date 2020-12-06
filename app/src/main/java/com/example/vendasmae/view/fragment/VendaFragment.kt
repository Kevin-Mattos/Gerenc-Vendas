package com.example.vendasmae.view.fragment

import android.app.AlertDialog
import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vendasmae.R
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.databinding.FragmentVendaBinding
import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.vendas.Venda
import com.example.vendasmae.entities.vendas.VendaVendedoraItem
import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.view.adapter.VendaAdapter
import com.example.vendasmae.view.viewmodel.VendaFragmentViewModel
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VendaFragment : BaseFragment(), AdapterView.OnItemSelectedListener, VendaAdapter.VendaAction {


    private lateinit var vendedoraAdapter: ArrayAdapter<Vendedora>
    private lateinit var prodAdapter: ArrayAdapter<Produto>
    val TAG = "VendaFragment"

    private val adapter by lazy{
        context?.let {
        VendaAdapter(it, this)
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
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.venda_action_menu, menu)
//        try {
//            val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//            (menu.findItem(R.id.search)?.actionView as SearchView).apply {
//                setSearchableInfo(searchManager.getSearchableInfo( activity!!.componentName))
//            }
//        }catch (ignore: Exception){
//
//        }
    }
    fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            Log.d(TAG, "$query")
            //use the query to search your data somehow
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


        mBinding.datePicker.setOnDateChangedListener { datePicker, year, wrongMong, day ->
            var month = wrongMong + 1

            val formattedDate = String.format("%4d-%02d-%02d", year, month, day)

           mViewModel.filterByDate(formattedDate)


        }

        mViewModel.getItemVendedor().observe(this, Observer{
            it?.let{ itensVendedora ->
                mViewModel.produtosVendedora = itensVendedora
            }
        })

    }

    override fun adiciona() {
        Log.d(TAG, "Adicionando venda")
        showDialog()

    }

    private fun showDialog(venda: Venda? = null) {




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

        builder.setOnDismissListener {
            mViewModel.selectedProduto = null
            mViewModel.selectedVendedora = null
        }



        prodAdapter = ArrayAdapter<Produto>(this.context!!, R.layout.support_simple_spinner_dropdown_item)
        prodAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)


        vendedoraAdapter = ArrayAdapter<Vendedora>(this.context!!, R.layout.support_simple_spinner_dropdown_item)
        vendedoraAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        val vend = mViewModel.produtosVendedora!!.map{it.vendedora}
        vendedoraAdapter.addAll(vend)

        val dialog = builder.create()

        dialog.show()

        venda?.let {
            dialog.findViewById<Spinner>(R.id.dialog_venda_vendedora_spinner).visibility = View.GONE
            dialog.findViewById<Spinner>(R.id.dialog_venda_item_spinner).visibility = View.GONE
            dialog.findViewById<TextView>(R.id.dialog_venda_title).setText("Atualizar Venda")
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText("Remover")
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setOnClickListener {
                mViewModel.remove(venda)
                dialog.dismiss()
            }
        }


        val theButton: Button = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        theButton.setOnClickListener {
            val valor = dialog.findViewById<EditText>(R.id.dialog_venda_valor).text.toString()
            val desconto = dialog.findViewById<EditText>(R.id.dialog_venda_desconto).text.toString()

            if(venda == null) {
                val newVenda = Venda(
                    1, valor.toFloatOrNull() ?: mViewModel.selectedProduto?.valor ?: 0f,
                    desconto.toFloatOrNull() ?: mViewModel.selectedProduto?.valor ?: 0f,
                    "", mViewModel.selectedProduto?.id ?: 0, mViewModel.selectedVendedora?.id
                )

                mViewModel.insere(newVenda)
            }else{
                venda.valor = valor.toFloatOrNull()?:venda.valor
                venda.desconto = desconto.toFloatOrNull()?:venda.desconto

                mViewModel.updateVenda(venda)
            }
            dialog.dismiss()
        }

        val prodSpinner = dialog.findViewById<Spinner>(R.id.dialog_venda_item_spinner)//.adapter = ada
        prodSpinner.adapter = prodAdapter
        prodSpinner.onItemSelectedListener = this

        val vendSpinner = dialog.findViewById<Spinner>(R.id.dialog_venda_vendedora_spinner)//.adapter = ada
        vendSpinner.adapter = vendedoraAdapter
        vendSpinner.onItemSelectedListener = this

        mViewModel.selectedProduto?.let {
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
                val t = mViewModel.produtosVendedora!!.flatMap { it.itens }.filter { (it!!.id_vendedora == mViewModel.selectedVendedora!!.id) && it.vendido == 0}
                prodAdapter.addAll(t)
                if(t.isNotEmpty())
                    mViewModel.selectedProduto = t[0]
            }
            is Produto ->{
                mViewModel.selectedProduto = p0.selectedItem as Produto

            }
        }

    }

    override fun onClick(vendaVendedoraItem: VendaVendedoraItem) {
        showDialog(vendaVendedoraItem.venda)
    }

    override fun onLongClick(vendaVendedoraItem: VendaVendedoraItem) {

    }



}