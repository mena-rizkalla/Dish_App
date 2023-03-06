package com.example.dishapp.view.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.Manifest.permission_group.CAMERA
import android.content.Intent
import android.provider.MediaStore
import android.webkit.PermissionRequest
import android.widget.Toast
import com.example.dishapp.databinding.ActivityAddUpdateDishBinding
import com.example.dishapp.databinding.DialogCustomImageSelectionBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

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

    private fun customImageSelectionDialog() {
        val dialog = Dialog(this)
        val binding: DialogCustomImageSelectionBinding =
            DialogCustomImageSelectionBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.tvCamera.setOnClickListener {

            Dexter.withContext(this).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(this@AddUpdateDishActivity,"you have permission now",Toast.LENGTH_SHORT).show()

                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    //add alert dialog later
                }

            }).onSameThread().check()

        }
    }
}