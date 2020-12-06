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
        fun onLongClick(vendaVendedoraItem: VendaVendedoraItem)
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

        fun vincula(vendaVendedoraItem: VendaVendedoraItem) {
            this.noticia = vendaVendedoraItem
            itemView.venda_vendedora.text = vendaVendedoraItem.vendedora?.nome?:"Sem vendedora"

            val valorTotal = vendaVendedoraItem.venda.valor
            val custo = vendaVendedoraItem.produto.valor
            val mae = vendaVendedoraItem.venda.valor - vendaVendedoraItem.venda.valor*vendaVendedoraItem.venda.desconto/100
            val vendedora = vendaVendedoraItem.venda.valor*vendaVendedoraItem.venda.desconto/100



            itemView.venda_valor_total.text = context.getString(R.string.venda_valor_format, valorTotal)
            itemView.venda_valor_mae.text = context.getString(R.string.venda_valor_format, mae)
            itemView.venda_valor_vendedora.text = context.getString(R.string.venda_valor_format, vendedora)
            itemView.venda_custo.text = context.getString(R.string.venda_valor_format, custo)

            itemView.venda_valor_total_p.text = context.getString(R.string.venda_valor_total_p, 100f)
            itemView.venda_lucro_valor_p.text = context.getString(R.string.venda_lucro_valor_p, 100f - vendaVendedoraItem.venda.desconto)
            itemView.venda_vendedora_valor_p.text = context.getString(R.string.venda_vendedora_valor_p, vendaVendedoraItem.venda.desconto)
            itemView.venda_custo_valor_p.text = context.getString(R.string.venda_custo_valor_p, custo / valorTotal * 100)

            itemView.venda_item_nome.text = vendaVendedoraItem.produto.nome
            itemView.venda_data.text = vendaVendedoraItem.venda.data
        }

    }

}