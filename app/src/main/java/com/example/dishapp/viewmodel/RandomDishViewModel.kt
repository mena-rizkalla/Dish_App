package com.example.dishapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dishapp.model.entities.RandomDish
import com.example.dishapp.model.network.RandomDishAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class RandomDishViewModel : ViewModel() {

    private val randomDishAPIService = RandomDishAPIService()

    private val compositeDisposable = CompositeDisposable()

    val randomDishLoaded = MutableLiveData<Boolean>()
    val randomDish = MutableLiveData<RandomDish.Recipes>()
    val randomDishLoadingError = MutableLiveData<Boolean>()

    fun getRandomDishFromAPI(){
        randomDishLoaded.value = true
        compositeDisposable.add(randomDishAPIService.getRandomDish().subscribeOn(Schedulers.newThread()).observeOn(
            AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<RandomDish.Recipes>() {
                override fun onSuccess(t: RandomDish.Recipes) {
                    randomDishLoaded.value = false
                    randomDish.value = t
                    randomDishLoadingError.value = false
                }

                override fun onError(e: Throwable) {
                    randomDishLoaded.value = false
                    randomDishLoadingError.value =true
                    e.printStackTrace()
                }

            }))
    }

}