package com.example.vendasmae.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vendasmae.R
import com.example.vendasmae.entities.vendedoras.Vendedora
import com.example.vendasmae.entities.vendedoras.VendedoraQuantidadeValor
import kotlinx.android.synthetic.main.vendedora_view.view.*

class VendedorasAdapter (private val context: Context, private val actions: VendedoraActions ,private val dataSet: MutableList<VendedoraQuantidadeValor> = mutableListOf()) :
    RecyclerView.Adapter<VendedorasAdapter.ViewHolder>() {
    private val TAG = "baseAdapter"

    interface VendedoraActions {
        fun longClickVendedora(vendedora: Vendedora)
        fun clickVendedora(vendedora: Vendedora)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewCriada = LayoutInflater.from(context)
            .inflate(
                R.layout.vendedora_view,
                parent, false
            )
        return ViewHolder(viewCriada)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = dataSet[position]
        holder.vincula(noticia)
    }

    fun atualiza(itens: List<VendedoraQuantidadeValor>) {
        notifyItemRangeRemoved(0, this.dataSet.size)
        this.dataSet.clear()
        this.dataSet.addAll(itens)
        notifyItemRangeInserted(0, this.dataSet.size)
    }



    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var vendedoraQuantidadeValor: VendedoraQuantidadeValor

        init {
            itemView.setOnClickListener {
                if (::vendedoraQuantidadeValor.isInitialized) {
                    actions.clickVendedora(vendedoraQuantidadeValor.vendedora)
                    Log.d(TAG, "${vendedoraQuantidadeValor.vendedora} clicado")
                }
            }
            itemView.setOnLongClickListener {
                if (::vendedoraQuantidadeValor.isInitialized) {
                    actions.longClickVendedora(vendedoraQuantidadeValor.vendedora)
                    Log.d(TAG, "${vendedoraQuantidadeValor.vendedora} clicado")
                }
                true
            }
        }

        fun vincula(vendedora: VendedoraQuantidadeValor) {
            this.vendedoraQuantidadeValor = vendedora
            itemView.vendedora_nome.text = vendedora.vendedora.nome
            itemView.vendedora_quantidade.text = "${vendedora.quantidadeVentido}"
            itemView.vendedora_valor.text = context.getString(R.string.vendedora_valor_format, vendedora.valorVendido)
        }

    }
}