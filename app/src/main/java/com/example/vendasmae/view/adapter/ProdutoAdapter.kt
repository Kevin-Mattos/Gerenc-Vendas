package com.example.vendasmae.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vendasmae.R
import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.itens.ProdutoVendedora
import kotlinx.android.synthetic.main.produto_view.view.*

class ProdutoAdapter (private val context: Context, val actions: ProdutoActions, private val dataSet: MutableList<ProdutoVendedora> = mutableListOf()) :
    RecyclerView.Adapter<ProdutoAdapter.ViewHolder>() {
    private val TAG = "ProdutoAdapter"


    interface ProdutoActions{
        fun onItemClick(produto: Produto)
        fun updateItem(produto: Produto)
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

    fun atualiza(itens: List<ProdutoVendedora>) {
        notifyItemRangeRemoved(0, this.dataSet.size)
        this.dataSet.clear()
        this.dataSet.addAll(itens)
        notifyItemRangeInserted(0, this.dataSet.size)
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var tipo: ProdutoVendedora

        init {
            itemView.setOnClickListener {
                if (::tipo.isInitialized) {
                    actions.onItemClick(tipo.produto)
                    Log.d(TAG, "${tipo.produto.nome} clicado")
                }
            }
        }

        fun vincula(produtoVendedora: ProdutoVendedora) {
            this.tipo = produtoVendedora
            itemView.produto_nome.text = produtoVendedora.produto.nome
            itemView.produto_vendedora.text = produtoVendedora.vendedora?.nome ?: "Ninguém"
            itemView.produto_valor.text = context.getString(R.string.produto_valor_format, produtoVendedora.produto.valor)
        }

    }

}