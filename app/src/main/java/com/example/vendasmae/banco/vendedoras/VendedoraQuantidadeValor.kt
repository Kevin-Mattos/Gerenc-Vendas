package com.example.vendasmae.banco.vendedoras

import androidx.room.Embedded
import androidx.room.Relation
import com.example.vendasmae.banco.itens.Item
import com.example.vendasmae.banco.vendas.Venda

class VendedoraQuantidadeValor(
    @Embedded
    val vendedora: Vendedora,
    val quantidadeVentido: Long,
    val valorVendido: Float)