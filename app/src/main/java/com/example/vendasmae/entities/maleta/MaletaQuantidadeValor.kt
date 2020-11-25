package com.example.vendasmae.entities.maleta

import androidx.room.Embedded

class MaletaQuantidadeValor (
    @Embedded
        val maleta: Maleta,
    val quantidadeEmEstoque: Long,
    val somaDeValores: Float?
    )
