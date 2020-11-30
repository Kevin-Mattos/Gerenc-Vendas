package com.example.vendasmae.entities.tipos

import androidx.room.Embedded

data class TipoQuantidadeValor(
    @Embedded
    val tipo: Tipo,
    val quantidadeEmEstoque: Long,
    val somaDeValores: Float?
)
