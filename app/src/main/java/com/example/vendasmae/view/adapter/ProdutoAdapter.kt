package com.example.vendasmae.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vendasmae.R
import com.example.vendasmae.entities.itens.Item
import com.example.vendasmae.entities.itens.ItemVendedora
import kotlinx.android.synthetic.main.produto_view.view.*

class ProdutoAdapter (private val context: Context, val actions: ProdutoActions, private val dataSet: MutableList<ItemVendedora> = mutableListOf()) :
    RecyclerView.Adapter<ProdutoAdapter.ViewHolder>() {
    private val TAG = "ProdutoAdapter"


    interface ProdutoActions{
        fun onItemClick(item: Item)
        fun updateItem(item: Item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewCriada = LayoutInflater.from(context)
            .inflate(
                R.layout.produto_view,
                parent, false
            )
        return ViewHolder(viewCriada)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = dataSet[position]
        holder.vincula(noticia)
    }

    fun atualiza(itens: List<ItemVendedora>) {
        notifyItemRangeRemoved(0, this.dataSet.size)
        this.dataSet.clear()
        this.dataSet.addAll(itens)
        notifyItemRangeInserted(0, this.dataSet.size)
    }



    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var tipo: ItemVendedora

        init {
            itemView.setOnClickListener {
                if (::tipo.isInitialized) {
                    actions.onItemClick(tipo.item)
                    Log.d(TAG, "${tipo.item.nome} clicado")
                }
            }
        }

        fun vincula(itemVendedora: ItemVendedora) {
            this.tipo = itemVendedora
            itemView.produto_nome.text = itemVendedora.item.nome
            itemView.produto_vendedora.text = itemVendedora.vendedora?.nome ?: "Ninguém"
            itemView.produto_valor.text = "${itemVendedora.item.valor}"
            itemView.produto_vendido_check_box.isChecked = itemVendedora.item.foiVendido()
            itemView.produto_vendido_check_box.setOnClickListener {
                itemVendedora.item.setVendido(itemView.produto_vendido_check_box.isChecked)
                actions.updateItem(itemVendedora.item)
            }


//            itemView.tipo_quantidade.text = "${tipo.quantidadeEmEstoque}"
//            itemView.tipo_valor.text = "${tipo.somaDeValores?:0}"

        }

    }

}