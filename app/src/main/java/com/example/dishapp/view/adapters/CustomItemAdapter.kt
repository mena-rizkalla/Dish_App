package com.example.dishapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.dishapp.databinding.FragmentAllDishesBinding
import com.example.dishapp.databinding.ItemCustomListBinding
import com.example.dishapp.view.activities.AddUpdateDishActivity
import com.example.dishapp.view.fragments.AllDishesFragment

class CustomItemAdapter(private val context: Context,
                        private val fragment: Fragment?,
                        private val listItems : List<String>,
                        private val selectedItem : String) :
    RecyclerView.Adapter<CustomItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemCustomListBinding = ItemCustomListBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.tvText.text = item

        holder.itemView.setOnClickListener {
            if (context is AddUpdateDishActivity){
                context.selectedListItem(item,selectedItem)
            }
            if(fragment is AllDishesFragment){
                fragment.filterSelection(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ViewHolder(view : ItemCustomListBinding) : RecyclerView.ViewHolder(view.root){
        val tvText = view.tvText
    }

}