package com.example.dishapp.model.database

import androidx.annotation.WorkerThread
import com.example.dishapp.model.entities.Dish

class DishRepository(private val dishDao : DishDao){


    //the function should be called from worker thread not main thread
    @WorkerThread
    suspend fun insertDishData(dish: Dish){
        dishDao.insert(dish)
    }
}