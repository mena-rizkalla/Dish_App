package com.example.dishapp.model.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "dishes_table")
data class Dish(
    @ColumnInfo var image : String,
    @ColumnInfo val imageSource : String,
    @ColumnInfo var title : String,
    @ColumnInfo var type : String,
    @ColumnInfo var category: String,
    @ColumnInfo var ingredient : String,
    @ColumnInfo var cookingTime : String,
    @ColumnInfo var directionToCook : String,
    @ColumnInfo var favoriteDish : Boolean = false,
    @PrimaryKey(autoGenerate = true) val id : Int =0
) : Parcelable