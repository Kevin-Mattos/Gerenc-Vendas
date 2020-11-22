package com.example.vendasmae.entities.itens

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.vendedoras.Vendedora

class ItemVendedora(
    @Embedded
    val item: Item,
    @Relation(parentColumn = "id_vendedora",
        entityColumn = "id") val vendedora: Vendedora?
)