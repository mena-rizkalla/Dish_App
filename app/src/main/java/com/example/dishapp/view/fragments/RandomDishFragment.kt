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
import com.example.dishapp.databinding.FragmentRandomDishBinding
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
                Toast.makeText(requireActivity(), it.recipes!![0]?.toString() ,Toast.LENGTH_SHORT).show()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}