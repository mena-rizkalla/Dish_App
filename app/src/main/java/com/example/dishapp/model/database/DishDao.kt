package com.example.dishapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dishapp.model.entities.Dish
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {

    @Insert
    suspend fun insert(dish : Dish)

    @Update
    suspend fun update(dish: Dish)

    @Query("SELECT * FROM dishes_table ORDER BY ID")
     fun getALLDishes() : Flow<List<Dish>>

     @Query("SELECT * FROM dishes_table WHERE favoriteDish = 1")
     fun getFavouriteDishes() : Flow<List<Dish>>
}