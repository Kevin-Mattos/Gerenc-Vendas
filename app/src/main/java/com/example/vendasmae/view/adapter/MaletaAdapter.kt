package com.example.vendasmae.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vendasmae.R
import com.example.vendasmae.entities.itens.Item
import com.example.vendasmae.entities.maleta.MaletaQuantidadeValor
import kotlinx.android.synthetic.main.maleta_view.view.*
import kotlinx.android.synthetic.main.tipo_view.view.*

class MaletaAdapter (private val context: Context, val actions: MaletaActions, private val dataSet: MutableList<MaletaQuantidadeValor> = mutableListOf()) :
    RecyclerView.Adapter<MaletaAdapter.ViewHolder>() {
    private val TAG = "ProdutoAdapter"


    interface MaletaActions{
        fun onItemClick(item: MaletaQuantidadeValor)
        fun updateItem(item: MaletaQuantidadeValor)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewCriada = LayoutInflater.from(context)
            .inflate(
                R.layout.maleta_view,
                parent, false
            )
        return ViewHolder(viewCriada)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = dataSet[position]
        holder.vincula(noticia)
    }

    fun atualiza(itens: List<MaletaQuantidadeValor>) {
        notifyItemRangeRemoved(0, this.dataSet.size)
        this.dataSet.clear()
        this.dataSet.addAll(itens)
        notifyItemRangeInserted(0, this.dataSet.size)
    }



    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var tipo: MaletaQuantidadeValor

        init {
            itemView.setOnClickListener {
                if (::tipo.isInitialized) {
                    actions.onItemClick(tipo)
                    Log.d(TAG, "${tipo.maleta.nome} clicado")
                }
            }
        }

        fun vincula(itemVendedora: MaletaQuantidadeValor) {
            this.tipo = itemVendedora
            itemView.maleta_nome.text = tipo.maleta.nome
            itemView.maleta_quantidade.text = "${tipo.quantidadeEmEstoque}"
            itemView.maleta_valor.text = "${tipo.somaDeValores?:0}"


        }

    }

}