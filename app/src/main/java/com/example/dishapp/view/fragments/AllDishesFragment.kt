package com.example.dishapp.view.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dishapp.R
import com.example.dishapp.application.DishApplication
import com.example.dishapp.databinding.FragmentAllDishesBinding
import com.example.dishapp.model.entities.Dish
import com.example.dishapp.view.activities.AddUpdateDishActivity
import com.example.dishapp.view.activities.MainActivity
import com.example.dishapp.view.adapters.DishAdapter
import com.example.dishapp.viewmodel.DishViewModel
import com.example.dishapp.viewmodel.DishViewModelFactory

class AllDishesFragment : Fragment() {

    private var _binding: FragmentAllDishesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var dishViewModel: DishViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentAllDishesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvDishesList.layoutManager = GridLayoutManager(requireActivity(),2)

        val viewModeFactory = DishViewModelFactory((requireActivity().application as DishApplication).repository)
        dishViewModel = ViewModelProvider(this,viewModeFactory).get(DishViewModel::class.java)

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

    fun dishDetails(dish: Dish){
        val action = AllDishesFragmentDirections.actionNavigationAllDishesToDishDetailsFragment(dish)
        findNavController().navigate(action)
        if (requireActivity() is MainActivity){
            (activity as MainActivity?)!!.hideBottomNavigationView()
        }
    }

    fun deleteDishData(dish: Dish){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete Dish")
        builder.setMessage("Are you sure you wants to delete this dish")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes"){dialogInterface , _ ->
            dishViewModel.delete(dish)
            dialogInterface.dismiss()
            Toast.makeText(context,"Deleted", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("No"){dialogInterface ,_ ->
            dialogInterface.dismiss()
        }

        val alertDialog : AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity){
            (activity as MainActivity?)!!.showBottomNavigationView()
        }
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