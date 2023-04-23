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

    @WorkerThread
    suspend fun updateDishDate(dish: Dish){
        dishDao.update(dish)
    }

    @WorkerThread
    suspend fun deleteDish(dish: Dish){
        dishDao.delete(dish)
    }

     fun getAllDishesList():Flow<List<Dish>>{
        return dishDao.getALLDishes()
    }

    fun getAllFavouriteDishes():Flow<List<Dish>>{
        return dishDao.getFavouriteDishes()
    }

    fun selectDishes(dishType : String):Flow<List<Dish>>{
        return dishDao.selectDishes(dishType)
    }
}