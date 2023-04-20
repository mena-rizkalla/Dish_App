package com.example.dishapp.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.dishapp.R
import com.example.dishapp.application.DishApplication
import com.example.dishapp.databinding.FragmentDishDetailsBinding
import com.example.dishapp.viewmodel.DishViewModel
import com.example.dishapp.viewmodel.DishViewModelFactory


class DishDetailsFragment : Fragment() {
    private var _binding : FragmentDishDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDishDetailsBinding.inflate(inflater,container,false)
       val view = binding.root





        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args : DishDetailsFragmentArgs by navArgs()
        args.let {
            binding.tvType.text = it.dishDetails.type
            binding.tvCategory.text = it.dishDetails.category
            Glide.with(this)
                .load(it.dishDetails.image).listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Palette.from(resource!!.toBitmap()).generate(){
                            pallete ->
                            val color = pallete?.vibrantSwatch?.rgb ?: 0;
                            binding.rlDishDetailMain.setBackgroundColor(color)
                        }
                        return false
                    }

                }).centerCrop().into(binding.ivDishImage)
            binding.tvIngredients.text = it.dishDetails.ingredient
            binding.tvTitle.text = it.dishDetails.title
            binding.tvCookingDirection.text = it.dishDetails.directionToCook
            binding.tvCookingTime.text = it.dishDetails.cookingTime

            if (args.dishDetails.favoriteDish){
                binding.ivFavoriteDish.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_favorite_24))
            }else{
                binding.ivFavoriteDish.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_unfavorite_24))
            }
        }

        binding.ivFavoriteDish.setOnClickListener {
            val dishViewModelFactory = DishViewModelFactory((requireActivity().application as DishApplication).repository)
            val dishViewModel = ViewModelProvider(this,dishViewModelFactory)[DishViewModel::class.java]

            args.dishDetails.favoriteDish = !args.dishDetails.favoriteDish
            dishViewModel.update(args.dishDetails)

            if (args.dishDetails.favoriteDish){
                binding.ivFavoriteDish.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_favorite_24))
            }else{
                binding.ivFavoriteDish.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_unfavorite_24))
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}