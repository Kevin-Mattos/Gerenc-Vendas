package com.example.vendasmae.entities.vendas

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vendasmae.entities.itens.Produto
import com.example.vendasmae.entities.vendedoras.Vendedora

data class VendaVendedoraItem (
    @Embedded val venda: Venda,
    @Relation(parentColumn = "id_vendedora",
        entityColumn = "id") val vendedora: Vendedora?,
    @Relation(parentColumn = "id_produto",
        entityColumn = "id") val produto: Produto
)