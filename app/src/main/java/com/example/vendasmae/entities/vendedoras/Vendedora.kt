package com.example.vendasmae.entities.vendedoras

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity
data class Vendedora (@PrimaryKey val id: Long, @NotNull val nome:String)