package com.example.flipkartgroceries.addProducts

import android.app.Activity
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
import com.example.flipkartgroceries.databinding.FragmentAddProductsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddProductsFragment : BaseFragment<FragmentAddProductsBinding>() {
    override fun getLayoutResource() = R.layout.fragment_add_products
    private val addProductsViewModel :AddProductsViewModel by viewModels()
    val selectProductImage=registerForActivityResult(ActivityResultContracts.GetContent()){uri->
        if (uri!=null){
            addProductsViewModel.selectProductImage.value=uri.toString()
        }
        else{
            Log.d(TAG,"Image is not available")
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //if ok user selected a file
        if (resultCode == Activity.RESULT_OK) {
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
        dataBinding.viewModel = addProductsViewModel
        initToolbar()

        lifecycleScope.launchWhenResumed {
            addProductsViewModel.selectImageEvent.collectLatest {
                selectProductImage.launch("image/*")
            }
        }

        lifecycleScope.launchWhenResumed {
            addProductsViewModel.addProductEvent.collectLatest {
               try {
                   val action =
                       AddProductsFragmentDirections.actionAddProductsFragmentToViewProductsFragment()
                   findNavController().navigate(action)
               }
               catch (e:Exception){
                   Log.d("TAG","Unable to load the image")
               }
            }
        }
        lifecycleScope.launchWhenResumed {
            addProductsViewModel.toastEvent.collectLatest {
                Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initToolbar(){
        dataBinding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}