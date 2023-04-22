package com.example.dishapp.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dishapp.R
import com.example.dishapp.model.entities.Dish
import com.example.dishapp.utils.Constants
import com.example.dishapp.view.activities.AddUpdateDishActivity
import com.example.dishapp.view.fragments.AllDishesFragment
import com.example.dishapp.view.fragments.FavoriteDishesFragment

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
            if (fragment is AllDishesFragment) {
                (fragment).dishDetails(dish)
            } else if (fragment is FavoriteDishesFragment){
                (fragment).dishDetails(dish)
            }
        }
            holder.moreBtn.setOnClickListener {

                val popUp = PopupMenu(fragment.context,holder.moreBtn)
                popUp.menuInflater.inflate(R.menu.menu_adapter,popUp.menu)

                popUp.setOnMenuItemClickListener {
                    if (it.itemId == R.id.edit_dish){
                        val intent = Intent(fragment.context,AddUpdateDishActivity::class.java)
                        intent.putExtra(Constants.EXTRA_DISH_DETAIL,dish)
                        fragment.requireActivity().startActivity(intent)
                    }else if(it.itemId == R.id.delete_dish){
                        (fragment as AllDishesFragment).deleteDishData(dish)
                    }
                    true
                }
                popUp.show()
            }

            if (fragment is AllDishesFragment){
                holder.moreBtn.visibility = View.VISIBLE
            }else{
                holder.moreBtn.visibility = View.GONE
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
        val moreBtn = itemView.findViewById<ImageButton>(R.id.ib_more)
    }
}