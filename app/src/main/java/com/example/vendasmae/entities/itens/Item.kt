package com.example.vendasmae.entities.itens

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.vendasmae.entities.tipos.Tipo
import org.jetbrains.annotations.NotNull

@Entity
class Item(
    @PrimaryKey val id: Long,
    @NotNull var nome: String,
    val valor: Float,
    val modelo: String?,
    @NotNull @ForeignKey(entity = Tipo::class,
    parentColumns = ["id"],
    childColumns = ["id_tipo"],
    onDelete = ForeignKey.CASCADE
    ) val id_tipo: Long,
    @ForeignKey(entity = Item::class,
        parentColumns = ["id"],
        childColumns = ["id_vendedora"],
        onDelete = ForeignKey.CASCADE
    ) val id_vendedora: Long
)