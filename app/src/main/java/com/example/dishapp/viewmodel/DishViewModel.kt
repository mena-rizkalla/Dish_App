package com.example.dishapp.viewmodel

import androidx.lifecycle.*
import com.example.dishapp.model.database.DishRepository
import com.example.dishapp.model.entities.Dish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DishViewModel(private val repository: DishRepository) : ViewModel() {


    fun insert(dish : Dish){
        viewModelScope.launch {
            repository.insertDishData(dish)
        }
    }

    fun update(dish : Dish){
        viewModelScope.launch {
            repository.updateDishDate(dish)
        }
    }

    fun delete(dish: Dish){
        viewModelScope.launch {
            repository.deleteDish(dish)
        }
    }

    fun getSelectedDish(dishType : String) :LiveData<List<Dish>>{
        return repository.selectDishes(dishType).asLiveData()
    }

    val allDishes : LiveData<List<Dish>> = repository.getAllDishesList().asLiveData()

    val allFavouriteDishes : LiveData<List<Dish>> = repository.getAllFavouriteDishes().asLiveData()
}

@Suppress("UNCHECKED_CAST")
class DishViewModelFactory(private val repository: DishRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DishViewModel::class.java))
            return DishViewModel(repository) as T
        throw IllegalArgumentException("Unknown View Model")
    }
}