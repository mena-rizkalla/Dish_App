package com.example.dishapp.model.network

import com.example.dishapp.model.entities.RandomDish
import com.example.dishapp.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RandomDishAPIService {

    private val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

     private val api = retrofit.create(RandomDishAPI::class.java)

    fun getRandomDish() : Single<RandomDish.Recipes>{
       return api.getRandomDishes(Constants.API_KEY_VALUE,
           Constants.LIMIT_LICENCE_VALUE,
           Constants.TAGS_VALUE,
           Constants.NUMBER_VALUE)
    }

}