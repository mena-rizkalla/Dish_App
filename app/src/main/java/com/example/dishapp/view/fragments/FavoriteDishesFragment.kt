package com.example.dishapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dishapp.application.DishApplication
import com.example.dishapp.databinding.FragmentFavoriteDishesBinding
import com.example.dishapp.model.entities.Dish
import com.example.dishapp.view.activities.MainActivity
import com.example.dishapp.view.adapters.DishAdapter
import com.example.dishapp.viewmodel.DishViewModel
import com.example.dishapp.viewmodel.DishViewModelFactory
import com.example.dishapp.viewmodel.FavoriteDishesViewModel

class FavoriteDishesFragment : Fragment() {

    private var _binding: FragmentFavoriteDishesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteDishesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val dishViewModelFactory = DishViewModelFactory((activity?.application as DishApplication).repository)
        val dishViewModel = ViewModelProvider(this,dishViewModelFactory)[DishViewModel::class.java]

        binding.rvDishesList.layoutManager = GridLayoutManager(requireActivity(),2)

        dishViewModel.allFavouriteDishes.observe(viewLifecycleOwner , Observer {
            val adapter = DishAdapter(this,it)
            binding.rvDishesList.adapter = adapter
        })




        return root
    }

    fun dishDetails(dish: Dish){
        val action = FavoriteDishesFragmentDirections.actionNavigationFavoriteDishesToDishDetailsFragment(dish)
        findNavController().navigate(action)
        if (requireActivity() is MainActivity){
            (activity as MainActivity?)!!.hideBottomNavigationView()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}