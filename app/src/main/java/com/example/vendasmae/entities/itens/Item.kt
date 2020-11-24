package com.example.vendasmae.entities.itens

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.vendasmae.entities.tipos.Tipo
import com.example.vendasmae.entities.vendedoras.Vendedora
import org.jetbrains.annotations.NotNull

@Entity
class Item(
    @PrimaryKey val id: Long,
    @NotNull var nome: String,
    var valor: Float,
    var modelo: String?,
    var vendido: Int,
    @NotNull @ForeignKey(entity = Tipo::class,
    parentColumns = ["id"],
    childColumns = ["id_tipo"],
    onDelete = ForeignKey.CASCADE
    ) var id_tipo: Long,
    @ForeignKey(entity = Vendedora::class,
        parentColumns = ["id"],
        childColumns = ["id_vendedora"],
        onDelete = ForeignKey.SET_NULL
    )
    var id_vendedora: Long? = -1
){
    override fun toString() = nome
}