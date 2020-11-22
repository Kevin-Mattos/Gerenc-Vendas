package com.example.vendasmae.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vendasmae.R
import com.example.vendasmae.banco.itens.Item
import com.example.vendasmae.banco.vendedoras.Vendedora
import kotlinx.android.synthetic.main.produto_view.view.*

class VendedorasAdapter (private val context: Context, private val dataSet: MutableList<Vendedora> = mutableListOf()) :
    RecyclerView.Adapter<VendedorasAdapter.ViewHolder>() {
    private val TAG = "baseAdapter"


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

    fun atualiza(itens: List<Vendedora>) {
        notifyItemRangeRemoved(0, this.dataSet.size)
        this.dataSet.clear()
        this.dataSet.addAll(itens)
        notifyItemRangeInserted(0, this.dataSet.size)
    }



    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var noticia: Vendedora

        init {
            itemView.setOnClickListener {
                if (::noticia.isInitialized) {
//                    quandoItemClicado(noticia)
                    Log.d(TAG, "${noticia.nome} clicado")
                }
            }
        }

        fun vincula(noticia: Vendedora) {
            this.noticia = noticia
            itemView.item_nome.text = noticia.nome
        }

    }
}