package com.example.dishapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dishapp.R
import com.example.dishapp.model.entities.Dish

class DishAdapter(private val fragment: Fragment , private var dishes : List<Dish>) : RecyclerView.Adapter<DishAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(fragment.context).inflate(R.layout.dish_item,parent,false)
        return DishAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishes.get(position)
        holder.title.text = dish.title
        fragment.context?.let { Glide.with(it).load(dish.image).into(holder.dishImage) }

        holder.itemView.setOnClickListener {
           fragment.findNavController().navigate(R.id.action_navigation_all_dishes_to_dishDetailsFragment)
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    fun dishesList(list: List<Dish>){
         dishes = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         val title = itemView.findViewById<TextView>(R.id.tv_dish_title)
         val dishImage = itemView.findViewById<ImageView>(R.id.iv_dish_image)
    }
}