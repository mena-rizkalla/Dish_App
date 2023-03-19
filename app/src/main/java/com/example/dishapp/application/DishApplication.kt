package com.example.dishapp.application

import android.app.Application
import com.example.dishapp.model.database.DishDatabase
import com.example.dishapp.model.database.DishRepository

class DishApplication : Application() {
    private val dataBase by lazy { DishDatabase.getInstance(this) }
    val repository by lazy { DishRepository(dataBase.dishDao) }
}