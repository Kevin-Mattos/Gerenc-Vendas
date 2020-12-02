package com.example.vendasmae.entities.maleta

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vendasmae.entities.vendedoras.Vendedora

class MaletaQuantidadeValor (
    @Embedded
    val maleta: Maleta,
    @Relation(parentColumn = "id_vendedora",
        entityColumn = "id")
    val vendedora: Vendedora? = null,

    val quantidadeEmEstoque: Long,
    val somaDeValores: Float?
    )
