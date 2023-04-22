package com.example.dishapp.utils

object Constants {

    const val DISH_TYPE : String = "DishType"
    const val DISH_CATEGORY : String = "DishCategory"
    const val DISH_COOKING_TIME : String = "DishCookingTime"

    const val IMAGE_SOURCE_LOCAL : String = "Local"
    const val IMAGE_SOURCE_ONLINE : String = "Online"

    const val EXTRA_DISH_DETAIL : String = "DishDetails"

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