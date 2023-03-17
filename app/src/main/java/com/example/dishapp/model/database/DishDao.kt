package com.example.dishapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.dishapp.model.entities.Dish

@Dao
interface DishDao {

    @Insert
    suspend fun insert(dish : Dish)
}