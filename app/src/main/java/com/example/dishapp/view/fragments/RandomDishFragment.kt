package com.example.dishapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.dishapp.databinding.FragmentRandomDishBinding
import com.example.dishapp.model.entities.RandomDish
import com.example.dishapp.viewmodel.DishViewModel
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

        })
        randomDishViewModel.randomDishLoadingError.observe(viewLifecycleOwner , Observer {
            it?.let {
                Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
            }
        })

        randomDishViewModel.randomDishLoaded.observe(viewLifecycleOwner , Observer {
            it?.let {
                Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}