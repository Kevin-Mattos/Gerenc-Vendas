package com.example.vendasmae.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vendasmae.R
import com.example.vendasmae.entities.vendas.VendaVendedoraItem
import kotlinx.android.synthetic.main.venda_view.view.*


class VendaAdapter(private val context: Context,val action: VendaAction ,val dataSet: MutableList<VendaVendedoraItem> = mutableListOf()) :
    RecyclerView.Adapter<VendaAdapter.ViewHolder>() {
    private val TAG = "baseAdapter"


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewCriada = LayoutInflater.from(context)
            .inflate(
                R.layout.venda_view,
                parent, false
            )
        return ViewHolder(viewCriada)
    }

    interface VendaAction{
        fun onClick(vendaVendedoraItem: VendaVendedoraItem)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = dataSet[position]
        holder.vincula(noticia)
    }

    fun atualiza(itens: List<VendaVendedoraItem>) {
        notifyItemRangeRemoved(0, this.dataSet.size)
        this.dataSet.clear()
        this.dataSet.addAll(itens)
        notifyItemRangeInserted(0, this.dataSet.size)
    }



    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var noticia: VendaVendedoraItem

        init {
            itemView.setOnClickListener {
                if (::noticia.isInitialized) {
//                    quandoItemClicado(noticia)
                    Log.d(TAG, "${noticia.vendedora?.nome?:"sem vendedora"} clicado")
                    action.onClick(noticia)
                }
            }
        }

        fun vincula(noticia: VendaVendedoraItem) {
            this.noticia = noticia
            itemView.venda_vendedora.text = noticia.vendedora?.nome?:"Sem vendedora"
            itemView.venda_valor.text = "R$ ${noticia.venda.valor}"
            itemView.venda_item_nome.text = noticia.produto.nome
            itemView.venda_data.text = noticia.venda.data
        }

    }

}