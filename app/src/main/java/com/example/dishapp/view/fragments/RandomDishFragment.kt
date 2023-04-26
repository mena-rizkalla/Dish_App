package com.example.dishapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.example.dishapp.R
import com.example.dishapp.application.DishApplication
import com.example.dishapp.databinding.FragmentRandomDishBinding
import com.example.dishapp.model.entities.Dish
import com.example.dishapp.model.entities.RandomDish
import com.example.dishapp.utils.Constants
import com.example.dishapp.viewmodel.DishViewModel
import com.example.dishapp.viewmodel.DishViewModelFactory
import com.example.dishapp.viewmodel.RandomDishViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RandomDishFragment : Fragment() {

    private var _binding: FragmentRandomDishBinding? = null
    private lateinit var randomDishViewModel: RandomDishViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = FragmentRandomDishBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.swipeRefresh.setOnRefreshListener {
            randomDishViewModel.getRandomDishFromAPI()
            binding.ivFavoriteDish.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_unfavorite_24))
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        randomDishViewModel = ViewModelProvider(this)[RandomDishViewModel::class.java]
        randomDishViewModel.getRandomDishFromAPI()
        randomDishViewModel.randomDish.observe(viewLifecycleOwner, Observer {

            it?.let {
                it.recipes!![0]?.let { it1 -> setUI(it1) }
            }

            if (binding.swipeRefresh.isRefreshing){
                binding.swipeRefresh.isRefreshing = false
            }

        })
        randomDishViewModel.randomDishLoadingError.observe(viewLifecycleOwner , Observer {
            it?.let {
                //Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
            }
        })

        randomDishViewModel.randomDishLoaded.observe(viewLifecycleOwner , Observer {
            it?.let {
                //Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setUI(randomDish : RandomDish.Recipe){
        Glide.with(requireActivity())
            .load(randomDish.image)
            .centerCrop()
            .into(binding.ivDishImage)
        binding.tvTitle.text = randomDish.title

        var dishType = "other"
        if (randomDish.dishTypes!!.isNotEmpty()){
            dishType = randomDish.dishTypes[0].toString()
        }
        binding.tvType.text = dishType
        binding.tvCategory.text = "other"

        var ingredient = ""
        if (randomDish.extendedIngredients!!.isNotEmpty()) {
            for (i in randomDish.extendedIngredients) {
                ingredient  = ingredient + " " + i!!.original
            }
        }
        binding.tvIngredients.text = ingredient

        binding.tvCookingDirection.text = randomDish.instructions

        binding.tvCookingTime.text = randomDish.cookingMinutes.toString()

        binding.ivFavoriteDish.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_unfavorite_24))
        var addedToFavorite = false

        binding.ivFavoriteDish.setOnClickListener {

            if (addedToFavorite){
                Toast.makeText(context,"Added to favourite",Toast.LENGTH_SHORT).show()
            }else {
                val dish = Dish(
                    randomDish.image!!,
                    Constants.IMAGE_SOURCE_ONLINE,
                    randomDish.title!!,
                    dishType,
                    "others",
                    ingredient,
                    randomDish.cookingMinutes.toString(),
                    randomDish.instructions!!, true
                )

                val dishViewModel: DishViewModel by viewModels {
                    DishViewModelFactory((requireActivity().application as DishApplication).repository)
                }
                dishViewModel.insert(dish)
                addedToFavorite = true
                binding.ivFavoriteDish.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_favorite_24))
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}