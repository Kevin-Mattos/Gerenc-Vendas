package com.example.vendasmae.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vendasmae.R
import com.example.vendasmae.entities.maleta.MaletaQuantidadeValor
import kotlinx.android.synthetic.main.maleta_view.view.*

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

        private lateinit var maletaQuantidadeValor: MaletaQuantidadeValor

        init {
            itemView.setOnClickListener {
                if (::maletaQuantidadeValor.isInitialized) {
                    actions.onItemClick(maletaQuantidadeValor)
                    Log.d(TAG, "${maletaQuantidadeValor.maleta.nome} clicado")
                }
            }
            itemView.setOnLongClickListener {
                if (::maletaQuantidadeValor.isInitialized) {
                    actions.updateItem(maletaQuantidadeValor)
                    Log.d(TAG, "${maletaQuantidadeValor.maleta.nome} long clicado")
                }
                true
            }

        }

        fun vincula(itemVendedora: MaletaQuantidadeValor) {
            this.maletaQuantidadeValor = itemVendedora
            itemView.maleta_nome.text = maletaQuantidadeValor.maleta.nome
            itemView.maleta_quantidade.text = String.format(context.getText(R.string.maleta_quantidade_itens).toString(),maletaQuantidadeValor.quantidadeEmEstoque.toInt())
            itemView.maleta_valor.text = String.format(context.getText(R.string.maleta_valor_contido).toString(), maletaQuantidadeValor.somaDeValores?:0f )
            itemView.maleta_vendedora.text = String.format(context.getText(R.string.maleta_esta_com).toString(), maletaQuantidadeValor.vendedora?.nome?:"Nenhuma")


        }

    }

}