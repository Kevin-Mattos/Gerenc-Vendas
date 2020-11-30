package com.example.vendasmae.entities.maleta

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Maleta(@PrimaryKey val id: Long, val nome: String) {
    override fun toString() = nome
}