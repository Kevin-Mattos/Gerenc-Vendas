package com.example.vendasmae.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vendasmae.MainActivity
import com.example.vendasmae.R
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.databinding.FragmentProdutoBinding
import com.example.vendasmae.entities.itens.Item
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.view.adapter.ProdutoAdapter
import com.example.vendasmae.view.adapter.VendedorasAdapter
import com.example.vendasmae.view.viewmodel.ProdutoFragmentViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class ProdutoFragment : BaseFragment(), ProdutoAdapter.ProdutoActions, AdapterView.OnItemSelectedListener {
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

    lateinit var mMainActivity: MainActivity

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
        mMainActivity = activity as MainActivity
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

        mViewModel.getTipos().observe(this, Observer {
            it?.let{
                mViewModel.tipos = it
            }
        })

        mViewModel.getVendedoras().observe(this, Observer {
            it?.let{
                mViewModel.vendedoras = it

            }
        })


        return mBinding.root
    }


    override fun adiciona() {
        Log.d(TAG, "adicionando")
        mViewModel.insere(Item(1, "carrinho", 236f, null, idProduto!!, 3))


                showDialog("Titulo", mViewModel.tipos, mViewModel.vendedoras)

    }

    private fun showDialog(title: String, itens: List<Tipo>, vendedoras: List<Vendedora>) {

        val builder = AlertDialog.Builder(this.context)
        // Get the layout inflater
        val inflater = requireActivity().layoutInflater;

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.custom_adiciona_produto_dialog, null))
        builder.setPositiveButton("Criar"){ dialog, widht ->
            dialog.dismiss()
            mViewModel.selectedVendedora
            mViewModel.selectedtipo
            mViewModel.insere(Item(1, "dialog", 246f, null, idProduto!!, 2))

        }

        builder.setNegativeButton("sair"){ dialog, widht ->

            dialog.dismiss()
        }



        val prodAdapter = ArrayAdapter<Tipo>(this.context!!, R.layout.support_simple_spinner_dropdown_item)
        prodAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        prodAdapter.addAll(itens)

        val vendedoraAdapter = ArrayAdapter<Vendedora>(this.context!!, R.layout.support_simple_spinner_dropdown_item)
        vendedoraAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        vendedoraAdapter.addAll(vendedoras)




        val dialog = builder.create()
        dialog.show()
        val prodSpinner = dialog.findViewById<Spinner>(R.id.dialog_produto_spinner)//.adapter = ada
        prodSpinner.adapter = prodAdapter
        prodSpinner.onItemSelectedListener = this

        val vendSpinner = dialog.findViewById<Spinner>(R.id.dialog_vendedora_spinner)//.adapter = ada
        vendSpinner.adapter = vendedoraAdapter
        vendSpinner.onItemSelectedListener = this

        mViewModel.selectedtipo?.let {
            prodSpinner.setSelection(mViewModel.getSelectedTipoPos())
        }

        mViewModel.selectedVendedora?.let {
            vendSpinner.setSelection(mViewModel.getSelectedVendedoraPos())
        }




//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
        //dialog.setContentView(R.layout.custom_adiciona_produto_dialog)
//        val body = dialog.findViewById(R.id.body) as TextView
//        body.text = title
//        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
//        val noBtn = dialog.findViewById(R.id.noBtn) as TextView
//        yesBtn.setOnClickListener {
//            dialog.dismiss()
//        }
//        noBtn.setOnClickListener { dialog.dismiss() }


    }

    override fun onTipoClick(id: Long) {
        Log.d(TAG, "produto clicado")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        when(p0!!.selectedItem){
            is Vendedora ->{
                mViewModel.selectedVendedora = p0!!.selectedItem as Vendedora
            }
            is Tipo ->{
                mViewModel.selectedtipo = p0!!.selectedItem as Tipo
            }
        }

    }

}