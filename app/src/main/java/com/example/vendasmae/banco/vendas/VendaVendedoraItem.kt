package com.example.vendasmae.banco.vendas

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vendasmae.banco.itens.Item
import com.example.vendasmae.banco.vendedoras.Vendedora

data class VendaVendedoraItem (
    @Embedded val venda: Venda,
    @Relation(parentColumn = "id_vendedora",
        entityColumn = "id") val vendedora: Vendedora,
    @Relation(parentColumn = "id_item",
        entityColumn = "id") val item: Item
)