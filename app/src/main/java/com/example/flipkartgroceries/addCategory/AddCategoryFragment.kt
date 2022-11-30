package com.example.flipkartgroceries.addCategory

import android.content.Intent
import android.graphics.Bitmap
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentAddCategoryBinding
import kotlinx.coroutines.flow.collectLatest

class AddCategoryFragment : BaseFragment<FragmentAddCategoryBinding, AddCategoryViewModel>() {
    override fun getViewModel() = AddCategoryViewModel::class.java
    lateinit var getContent: ActivityResultLauncher<String>
    override fun getLayoutResource() = R.layout.fragment_add_category

    //    private val pickPicture= registerForActivityResult(ActivityResultContracts.GetContent())
    override fun setUp() {
        dataBinding.viewModel = viewModel
        lifecycleScope.launchWhenResumed {
            viewModel.selectImageEvent.collectLatest {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 456)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.submitBtnEvent.collectLatest {
                val action =
                    AddCategoryFragmentDirections.actionAddCategoryFragmentToViewCategoriesFragment()
                findNavController().navigate(action)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            var bitmap = data?.extras?.get("data ") as Bitmap
//            dataBinding.selectImg.setImageBitMap
        }
    }


}