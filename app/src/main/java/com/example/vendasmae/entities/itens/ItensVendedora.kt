package com.example.vendasmae.entities.itens

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vendasmae.entities.vendedoras.Vendedora

class ItensVendedora(
@Embedded
val vendedora: Vendedora?,

    @Relation(parentColumn = "id",
    entityColumn = "id_vendedora") val itens: List<Item?>
)