package com.example.dishapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dishapp.model.entities.Dish
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {

    @Insert
    suspend fun insert(dish : Dish)

    @Query("SELECT * FROM dishes_table ORDER BY ID")
     fun getALLDishes() : Flow<List<Dish>>
}