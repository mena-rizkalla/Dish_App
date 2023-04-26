package com.example.dishapp.utils

object Constants {

    const val DISH_TYPE : String = "DishType"
    const val DISH_CATEGORY : String = "DishCategory"
    const val DISH_COOKING_TIME : String = "DishCookingTime"

    const val IMAGE_SOURCE_LOCAL : String = "Local"
    const val IMAGE_SOURCE_ONLINE : String = "Online"

    const val EXTRA_DISH_DETAIL : String = "DishDetails"

    const val BASE_URL="https://api.spoonacular.com/"

    const val API_ENDPOINT :String = "recipes/random"

    const val API_KEY : String = "apiKey"
    const val LIMIT_LICENCE : String = "limitLicence"
    const val TAGS : String = "tags"
    const val NUMBER : String = "number"



    const val API_KEY_VALUE : String = "8d7e769c704c49e7ab1ba74f8bf49062"
    const val LIMIT_LICENCE_VALUE : Boolean = true
    const val TAGS_VALUE : String = "vegetarian,dessert"
    const val NUMBER_VALUE : Int = 1

    fun dishTypes():ArrayList<String>{
        val dishTypes = ArrayList<String>()
        dishTypes.add("breakfast")
        dishTypes.add("lunch")
        dishTypes.add("snacks")
        dishTypes.add("dinner")
        dishTypes.add("other")
        return dishTypes
    }

    fun dishCategories(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("Pizza")
        list.add("Burger")
        list.add("Cafe")
        list.add("Chicken")
        list.add("Dessert")
        list.add("Drinks")
        list.add("Hot Dogs")
        list.add("Juices")
        list.add("Sandwich")
        list.add("Tea & Coffee")
        list.add("Other")
        return list
    }

    fun dishCookTime(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("10")
        list.add("15")
        list.add("20")
        list.add("30")
        list.add("45")
        list.add("50")
        list.add("60")
        list.add("90")
        list.add("120")
        list.add("150")
        list.add("180")
        return list
    }
}