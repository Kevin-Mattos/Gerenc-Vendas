package com.example.vendasmae.entities.tipos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tipo (@PrimaryKey val id: Long, var nome: String){
    override fun toString() = nome
}