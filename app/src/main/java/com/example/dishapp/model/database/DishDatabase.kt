package com.example.dishapp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dishapp.model.entities.Dish

@Database(entities = [Dish::class] , version = 1 , exportSchema = false)
abstract class DishDatabase : RoomDatabase(){

    abstract val dishDao : DishDao

    companion object{

        @Volatile
        private var INSTANCE : DishDatabase? = null

        fun getInstance(context: Context):DishDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        DishDatabase::class.java,
                        "dishes_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}