package com.example.vendasmae.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vendasmae.MainActivity
import com.example.vendasmae.R
import com.example.vendasmae.baseClass.BaseFragment
import com.example.vendasmae.databinding.FragmentProdutoBinding
import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.maleta.Maleta
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.view.adapter.ProdutoAdapter
import com.example.vendasmae.view.viewmodel.ProdutoFragmentViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class ProdutoFragment : BaseFragment(), ProdutoAdapter.ProdutoActions, AdapterView.OnItemSelectedListener {
    // TODO: Rename and change types of parameters
    private var idTipo: Long? = null
    private var idMaleta: Long? = null

    companion object{
        const val idTipoProduto = "idtiop"
        const val idMaletaProduto = "idMaleta"
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
            val tip  = it.getLong(idTipoProduto, -1)
            val mal = it.getLong(idMaletaProduto, -1)
            idTipo = if(tip == -1.toLong()) null else tip
            idMaleta = if(mal == -1.toLong()) null else mal
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


        idTipo?.let{
            mViewModel.getItemVendedora(idTipo!!).observe(this, androidx.lifecycle.Observer {

                adapter.atualiza(it)
            })
        }

        idMaleta?.let{
            mViewModel.getItemMaleta(idMaleta!!).observe(this, androidx.lifecycle.Observer {
                it?.let{
                    adapter.atualiza(it)
                }

            })
        }





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

        mViewModel.getMaletas().observe(this, Observer {
            it?.let{
                mViewModel.maletas = it

            }
        })


        return mBinding.root
    }


    override fun adiciona() {
        Log.d(TAG, "adicionando")
        //mViewModel.insere(Item(1, "carrinho", 236f, null, 0,idTipo!!, 3))
        showDialog(null, mViewModel.tipos, mViewModel.vendedoras, mViewModel.maletas)

    }

    private fun showDialog(produto: Produto?, tipos: List<Tipo>, vendedoras: List<Vendedora>, maletas: List<Maleta>) {




        val builder = AlertDialog.Builder(this.context)
        // Get the layout inflater
        val inflater = requireActivity().layoutInflater;

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.custom_adiciona_produto_dialog, null))
        builder.setPositiveButton("Criar"){ dialog, widht ->

        }

        builder.setNegativeButton("sair"){ dialog, widht ->

            dialog.dismiss()
        }



        val prodAdapter = ArrayAdapter<Tipo>(this.context!!, R.layout.support_simple_spinner_dropdown_item)
        prodAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        prodAdapter.addAll(tipos)

        val vendedoraAdapter = ArrayAdapter<Vendedora>(this.context!!, R.layout.support_simple_spinner_dropdown_item)
        vendedoraAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        vendedoraAdapter.addAll(vendedoras)

        val maletaAdapter = ArrayAdapter<Maleta>(this.context!!, R.layout.support_simple_spinner_dropdown_item)
        maletaAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        maletaAdapter.add(Maleta(-1, "Nenhuma"))
        maletaAdapter.addAll(maletas)




        val dialog = builder.create()
        dialog.show()
        produto?.let { item ->
            dialog.findViewById<EditText>(R.id.dialog_prod_nome).setText(item.nome)
             dialog.findViewById<EditText>(R.id.dialog_prod_modelo).setText(item.modelo)
             dialog.findViewById<EditText>(R.id.dialog_prod_valor).setText(item.valor.toString())

            mViewModel.selectedVendedora = vendedoras.firstOrNull { it.id == item.id_vendedora }
            mViewModel.selectedtipo = tipos.firstOrNull { it.id == item.id_tipo }
            mViewModel.selectedMaleta = maletas.firstOrNull { it.id == item.id_maleta }

        }



        val theButton: Button = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        theButton.setOnClickListener {
            val nome = dialog.findViewById<EditText>(R.id.dialog_prod_nome).text.toString()
            val modelo = dialog.findViewById<EditText>(R.id.dialog_prod_modelo).text.toString()
            val valor = dialog.findViewById<EditText>(R.id.dialog_prod_valor).text.toString()
            //val nome = dialog.findViewById<EditText>(R.id.dialog_prod_nome).text.toString()
            //val nome = dialog.findViewById<EditText>(R.id.dialog_prod_nome).text.toString()
            if(produto != null){
                produto.nome = nome
                produto.modelo = modelo
                produto.valor = valor.toFloatOrNull()?:0f
                produto.id_vendedora = mViewModel.selectedVendedora!!.id
                produto.id_tipo = mViewModel.selectedtipo!!.id
                produto.setIdMaleta(mViewModel.selectedMaleta!!.id)
                mViewModel.update(produto)
            }
            else {
                val itemToInsert = Produto(1, if(nome.isNotBlank()) nome else "Sem Nome", valor.toFloatOrNull()?:46.6f,
                    if(modelo.isNotBlank()) modelo else null, 0,mViewModel.selectedtipo?.id?:0,
                    if(mViewModel.selectedVendedora!!.id == -1.toLong()) null else mViewModel.selectedVendedora!!.id)
                itemToInsert.setIdMaleta(mViewModel.selectedMaleta!!.id)

                mViewModel.insere(itemToInsert)
            }
            dialog.dismiss()
        }

        val prodSpinner = dialog.findViewById<Spinner>(R.id.dialog_produto_spinner)//.adapter = ada
        prodSpinner.adapter = prodAdapter
        prodSpinner.onItemSelectedListener = this

        val vendSpinner = dialog.findViewById<Spinner>(R.id.dialog_vendedora_spinner)//.adapter = ada
        vendSpinner.adapter = vendedoraAdapter
        vendSpinner.onItemSelectedListener = this

        val maletaSpinner = dialog.findViewById<Spinner>(R.id.dialog_maleta_spinner)//.adapter = ada
        maletaSpinner.adapter = maletaAdapter
        maletaSpinner.onItemSelectedListener = this

        mViewModel.selectedtipo?.let {
            prodSpinner.setSelection(mViewModel.getSelectedTipoPos())

        }?:prodSpinner.setSelection(mViewModel.tipos.indexOfFirst { it.id == idTipo })

        mViewModel.selectedVendedora?.let {
            vendSpinner.setSelection(mViewModel.getSelectedVendedoraPos())
        }
        mViewModel.selectedMaleta?.let {
            maletaSpinner.setSelection(mViewModel.getSelectedMaletaPos() + 1)
        }


    }

    override fun onItemClick(produto: Produto) {
        Log.d(TAG, "produto clicado")
        showDialog(produto, mViewModel.tipos, mViewModel.vendedoras, mViewModel.maletas)
    }

    override fun updateItem(produto: Produto) {
        mViewModel.update(produto)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        when(p0?.selectedItem){
            is Vendedora ->{
                mViewModel.selectedVendedora = p0.selectedItem as Vendedora
            }
            is Tipo ->{
                mViewModel.selectedtipo = p0.selectedItem as Tipo
            }
            is Maleta -> {
                mViewModel.selectedMaleta = p0.selectedItem as Maleta
            }
        }

    }

}