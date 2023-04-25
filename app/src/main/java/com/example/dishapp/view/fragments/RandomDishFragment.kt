package com.example.dishapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dishapp.databinding.FragmentRandomDishBinding
import com.example.dishapp.viewmodel.RandomDishViewModel

class RandomDishFragment : Fragment() {

    private var _binding: FragmentRandomDishBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(RandomDishViewModel::class.java)

        _binding = FragmentRandomDishBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}