package com.example.vendasmae.banco.tipos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tipo (@PrimaryKey val id: Long, var nome: String)