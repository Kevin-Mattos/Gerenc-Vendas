package com.example.vendasmae.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vendasmae.R
import com.example.vendasmae.banco.tipos.TipoQuantidadeValor
import kotlinx.android.synthetic.main.tipo_view.view.*

class TipoAdapter(private val context: Context, private val dataSet: MutableList<TipoQuantidadeValor> = mutableListOf()) :
    RecyclerView.Adapter<TipoAdapter.ViewHolder>() {
    private val TAG = "baseAdapter"


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewCriada = LayoutInflater.from(context)
            .inflate(
                R.layout.tipo_view,
                parent, false
            )
        return ViewHolder(viewCriada)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = dataSet[position]
        holder.vincula(noticia)
    }

    fun atualiza(itens: List<TipoQuantidadeValor>) {
        notifyItemRangeRemoved(0, this.dataSet.size)
        this.dataSet.clear()
        this.dataSet.addAll(itens)
        notifyItemRangeInserted(0, this.dataSet.size)
    }



    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private lateinit var tipo: TipoQuantidadeValor

        init {
            itemView.setOnClickListener {
                if (::tipo.isInitialized) {
//                    quandoItemClicado(noticia)
                    Log.d(TAG, "${tipo.tipo.nome} clicado")
                }
            }
        }

        fun vincula(tipo: TipoQuantidadeValor) {
            this.tipo = tipo
            itemView.tipo_nome.text = tipo.tipo.nome
            itemView.tipo_quantidade.text = "${tipo.quantidadeEmEstoque}"
            itemView.tipo_valor.text = "${tipo.somaDeValores}"

        }

    }

}