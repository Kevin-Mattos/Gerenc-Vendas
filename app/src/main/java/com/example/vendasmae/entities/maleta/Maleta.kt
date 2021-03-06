package com.example.vendasmae.entities.maleta

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.vendasmae.entities.vendedoras.Vendedora

@Entity
class Maleta(@PrimaryKey val id: Long,
             var nome: String,
             @ForeignKey(entity = Vendedora::class,
                 parentColumns = ["id"],
                 childColumns = ["id_vendedora"],
                 onDelete = ForeignKey.SET_DEFAULT
             ) var id_vendedora: Long?  = null
) {
    override fun toString() = nome
}