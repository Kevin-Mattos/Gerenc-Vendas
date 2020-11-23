package com.example.vendasmae.entities.itens

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.vendasmae.entities.tipos.Tipo
import org.jetbrains.annotations.NotNull

@Entity
class Item(
    @PrimaryKey val id: Long,
    @NotNull var nome: String,
    var valor: Float,
    var modelo: String?,
    var vendido: Int = 0,
    @NotNull @ForeignKey(entity = Tipo::class,
    parentColumns = ["id"],
    childColumns = ["id_tipo"],
    onDelete = ForeignKey.CASCADE
    ) var id_tipo: Long,
    @ForeignKey(entity = Item::class,
        parentColumns = ["id"],
        childColumns = ["id_vendedora"],
        onDelete = ForeignKey.SET_NULL
    ) var id_vendedora: Long?
){

    fun foiVendido() = vendido == 1

    fun setVendido(value: Boolean){
        vendido = if(value) 1 else 0
    }


    override fun toString() = nome
}