package com.example.dishapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes_table")
data class Dish(
    @ColumnInfo val image : String,
    @ColumnInfo val imageSource : String,
    @ColumnInfo val title : String,
    @ColumnInfo val type : String,
    @ColumnInfo val category: String,
    @ColumnInfo val ingredient : String,
    @ColumnInfo val cookingTime : String,
    @ColumnInfo val directionToCook : String,
    @ColumnInfo val favoriteDish : Boolean = false,
    @PrimaryKey(autoGenerate = true) val id : Int =0
)