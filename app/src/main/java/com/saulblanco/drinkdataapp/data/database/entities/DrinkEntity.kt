package com.saulblanco.drinkdataapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain

@Entity(tableName="drink_table")
class DrinkEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "alcoholic") val alcoholic: String?,
    @ColumnInfo(name = "tipoVaso") val tipoVaso: String?,
    @ColumnInfo(name = "category") val category: String?
)
fun DrinkGeneralDomain.toDatabase() = DrinkEntity(id,name,image,alcoholic,tipoVaso, category)