package com.example.flipkartgroceries.addCategory

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //if ok user selected a file
        if (resultCode == RESULT_OK) {
            val sourceTreeUri: Uri? = data?.data
            if (sourceTreeUri != null) {
                requireContext().contentResolver.takePersistableUriPermission(
                    sourceTreeUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
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
        lifecycleScope.launchWhenResumed {
            addCategoryViewModel.toastEvent.collectLatest {
                Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
            }
        }

    }


}