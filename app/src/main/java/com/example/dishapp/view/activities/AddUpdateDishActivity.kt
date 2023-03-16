package com.example.dishapp.view.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.dishapp.R
import com.example.dishapp.databinding.ActivityAddUpdateDishBinding
import com.example.dishapp.databinding.DialogCustomImageSelectionBinding
import com.example.dishapp.databinding.DialogCustomListBinding
import com.example.dishapp.utils.Constants
import com.example.dishapp.view.adapters.CustomItemAdapter
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class AddUpdateDishActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddUpdateDishBinding
    private var imagePath : String = ""
    private lateinit var customListDialog : Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivAddDishImage.setOnClickListener {
            customImageSelectionDialog()
        }

        binding.etType.setOnClickListener {
            customItemDialog("Dish Type",Constants.dishTypes(),Constants.DISH_TYPE)
        }

        binding.etCategory.setOnClickListener {
            customItemDialog("Dish Category",Constants.dishCategories(),Constants.DISH_CATEGORY)
        }
        binding.etCookingTime.setOnClickListener{
            customItemDialog("Select Time" , Constants.dishCookTime(),Constants.DISH_COOKING_TIME)
        }

        binding.btnAddDish.setOnClickListener {
            val title = binding.etTitle.text.toString().trim { it <= ' ' }
            val type = binding.etType.text.toString().trim { it <= ' ' }
            val category = binding.etCategory.text.toString().trim { it <= ' ' }
            val ingredients = binding.etIngredients.text.toString().trim { it <= ' ' }
            val cookingTimeInMinutes = binding.etCookingTime.text.toString().trim { it <= ' ' }
            val cookingDirection = binding.etDirectionToCook.text.toString().trim { it <= ' ' }

            when{
                TextUtils.isEmpty(title) -> {
                    Toast.makeText(this,"Add Title",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(type) -> {
                    Toast.makeText(this,"Add Type",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(category) -> {
                    Toast.makeText(this,"Add Category",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(ingredients) -> {
                    Toast.makeText(this,"Add Ingredients",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(cookingTimeInMinutes) -> {
                    Toast.makeText(this,"Add Cooking Time",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(cookingDirection) -> {
                    Toast.makeText(this,"Add Cooking Direction",Toast.LENGTH_SHORT).show()
                }
            }
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
                Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (report.areAllPermissionsGranted()) {
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(intent, CAMERA)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    showDialogForPermissions()
                }

            }).onSameThread().check()
            dialog.dismiss()
        }


        binding.tvGallery.setOnClickListener {
            Dexter.withContext(this).withPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        startActivityForResult(intent, GALLERY)
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        Toast.makeText(
                            this@AddUpdateDishActivity,
                            "You have denied the storage permission",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: com.karumi.dexter.listener.PermissionRequest?,
                        p1: PermissionToken?
                    ) {
                        showDialogForPermissions()
                    }
                }).onSameThread().check()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showDialogForPermissions(){
        AlertDialog.Builder(this).setMessage("Some permissions are required first")
            .setPositiveButton("Go to setting") { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName,null)
                    intent.data = uri
                    startActivity(intent)
                }catch (e : ActivityNotFoundException){
                    e.printStackTrace()
                }
            }.setNegativeButton("Cancel"){
                    dialog, _ -> dialog.dismiss()
            }.show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == CAMERA){
                data?.let {
                    val image : Bitmap = data.extras!!.get("data") as Bitmap
                    Glide.with(this)
                        .load(image)
                        .centerCrop()
                        .into(binding.ivDishImage)
                    binding.ivAddDishImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_edit_24))
                    imagePath = saveImageToInternalStorage(image)
                }
            }

            if (requestCode == GALLERY){
                data?.let {
                    val selectedImgUri = data.data
                    Glide.with(this)
                        .load(selectedImgUri)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(object  : RequestListener<Drawable>{
                            override fun onLoadFailed(e: GlideException?, model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                resource?.let {
                                    val bitmap : Bitmap = resource.toBitmap()
                                    imagePath = saveImageToInternalStorage(bitmap)
                                }
                                return false
                            }

                        })
                        .into(binding.ivDishImage)
                    binding.ivAddDishImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_edit_24))
                }
            }
        }
    }

    // function to take care of saving images to internal storage
    // return path of the file
    private fun saveImageToInternalStorage(bitmap: Bitmap):String{
        val wrapper = ContextWrapper(applicationContext)

        var file = wrapper.getDir(IMAGE_DIRECTORY,Context.MODE_PRIVATE)
        file = File(file,"${UUID.randomUUID()}.jpg")
        try {
            val stream : OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
            stream.flush()
            stream.close()
        }catch (e : IOException){
            e.printStackTrace()
        }
        return file.absolutePath
    }

    private fun customItemDialog(title:String , itemList: List<String>,selection:String){
         customListDialog = Dialog(this)
        val binding : DialogCustomListBinding = DialogCustomListBinding.inflate(layoutInflater)
       customListDialog.setContentView(binding.root)

        binding.tvTitle.text = title
        binding.rvList.layoutManager = LinearLayoutManager(this)
        val adapter = CustomItemAdapter(this,itemList,selection)
        binding.rvList.adapter = adapter
        customListDialog.show()
    }

    fun selectedListItem(item:String,selection: String){
        when(selection){
            Constants.DISH_TYPE -> {
                customListDialog.dismiss()
                binding.etType.setText(item)
            }
            Constants.DISH_CATEGORY -> {
                customListDialog.dismiss()
                binding.etCategory.setText(item)
            }
            Constants.DISH_COOKING_TIME -> {
                customListDialog.dismiss()
                binding.etCookingTime.setText(item)
            }
        }
    }


    companion object {
        private const val CAMERA = 1
        private const val GALLERY = 2

        private const val IMAGE_DIRECTORY = "DishImages"
    }
}