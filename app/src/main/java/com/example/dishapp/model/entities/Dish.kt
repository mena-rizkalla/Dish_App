package com.example.dishapp.model.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
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
    @ColumnInfo var favoriteDish : Boolean = false,
    @PrimaryKey(autoGenerate = true) val id : Int =0
) : Parcelable