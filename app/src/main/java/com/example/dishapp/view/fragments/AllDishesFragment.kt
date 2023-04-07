package com.example.dishapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dishapp.R
import com.example.dishapp.application.DishApplication
import com.example.dishapp.databinding.FragmentAllDishesBinding
import com.example.dishapp.view.activities.AddUpdateDishActivity
import com.example.dishapp.view.adapters.DishAdapter
import com.example.dishapp.viewmodel.DishViewModel
import com.example.dishapp.viewmodel.DishViewModelFactory

class AllDishesFragment : Fragment() {

    private var _binding: FragmentAllDishesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentAllDishesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvDishesList.layoutManager = GridLayoutManager(requireActivity(),2)

        val viewModeFactory = DishViewModelFactory((requireActivity().application as DishApplication).repository)
        val dishViewModel = ViewModelProvider(this,viewModeFactory).get(DishViewModel::class.java)

        dishViewModel.allDishes.observe(viewLifecycleOwner , Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    binding.rvDishesList.visibility = View.VISIBLE
                    binding.tvNoDishesAddedYet.visibility = View.GONE
                    val adapter = DishAdapter(this, it)
                    binding.rvDishesList.adapter = adapter
                }else{
                    binding.rvDishesList.visibility = View.GONE
                    binding.tvNoDishesAddedYet.visibility = View.VISIBLE
                }
            }

        })

        setHasOptionsMenu(true)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_all_dishes,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add_dish -> {
                startActivity(Intent(requireActivity(),AddUpdateDishActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}