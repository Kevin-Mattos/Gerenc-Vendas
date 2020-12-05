package com.example.vendasmae.entities.vendas

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.vendedoras.Vendedora
import org.jetbrains.annotations.NotNull


@Entity
class Venda (@PrimaryKey var id: Long,
             @NotNull var valor: Float,
             @NotNull var desconto: Float,
             var data: String,
             @NotNull @ForeignKey(entity = Produto::class,
                    parentColumns = ["id"],
                    childColumns = ["id_produto"],
                    onDelete = CASCADE)
             val id_produto: Long,
             @ForeignKey(entity = Vendedora::class,
                    parentColumns = ["id"],
                    childColumns = ["id_vendedora"]) val id_vendedora: Long?)