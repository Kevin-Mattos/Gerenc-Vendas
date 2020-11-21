package com.example.vendasmae.banco.itens

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
class Item(@PrimaryKey val id: Long, @NotNull var nome: String)