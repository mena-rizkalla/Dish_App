package com.example.dishapp.view.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dishapp.R
import com.example.dishapp.databinding.ActivityAddUpdateDishBinding
import com.example.dishapp.databinding.DialogCustomImageSelectionBinding

class AddUpdateDishActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddUpdateDishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivAddDishImage.setOnClickListener {
            customImageSelectionDialog()
        }

    }

    private fun customImageSelectionDialog(){
        val dialog = Dialog(this)
        val binding : DialogCustomImageSelectionBinding =
            DialogCustomImageSelectionBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

            dialog.show()
        }
}