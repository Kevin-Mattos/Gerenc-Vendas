package com.example.vendasmae.entities.vendedoras

import androidx.room.Embedded

class VendedoraQuantidadeValor(
    @Embedded
    val vendedora: Vendedora,
    val quantidadeVentido: Long,
    val valorVendido: Float)