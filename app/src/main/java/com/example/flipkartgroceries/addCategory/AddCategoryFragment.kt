package com.example.flipkartgroceries.addCategory

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentAddCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddCategoryFragment : BaseFragment<FragmentAddCategoryBinding>() {
    override fun getLayoutResource() = R.layout.fragment_add_category
    private val addCategoryViewModel: AddCategoryViewModel by viewModels()

    val pickPicture = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            addCategoryViewModel.categoryImage.value= uri.toString()

        } else {
            Log.d(TAG, "Image is not selected")
        }
    }

    override fun setUp() {
        dataBinding.viewModel = addCategoryViewModel
        lifecycleScope.launchWhenResumed {
            addCategoryViewModel.selectImageEvent.collectLatest {
                pickPicture.launch("image/*")

            }
        }
        lifecycleScope.launchWhenResumed {
            addCategoryViewModel.submitBtnEvent.collectLatest {
                val action =
                    AddCategoryFragmentDirections.actionAddCategoryFragmentToViewCategoriesFragment()
                findNavController().navigate(action)
            }
        }

    }


}