package com.example.dishapp.model.network

import com.example.dishapp.model.entities.RandomDish
import com.example.dishapp.utils.Constants
import io.reactivex.rxjava3.core.Single

import retrofit2.http.GET
import retrofit2.http.Query

interface RandomDishAPI {

    @GET(Constants.API_ENDPOINT)
     fun getRandomDishes(
        @Query(Constants.API_KEY) apiKey : String ,
        @Query(Constants.LIMIT_LICENCE) limitLicence : Boolean ,
        @Query(Constants.TAGS) tags : String,
        @Query(Constants.NUMBER) number : Int
     ):Single<RandomDish.Recipes>

}