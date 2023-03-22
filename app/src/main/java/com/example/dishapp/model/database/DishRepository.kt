package com.example.dishapp.model.database

import androidx.annotation.WorkerThread
import com.example.dishapp.model.entities.Dish
import kotlinx.coroutines.flow.Flow

class DishRepository(private val dishDao : DishDao){


    //the function should be called from worker thread not main thread
    @WorkerThread
    suspend fun insertDishData(dish: Dish){
        dishDao.insert(dish)
    }

     fun getAllDishesList():Flow<List<Dish>>{
        return dishDao.getALLDishes()
    }
}