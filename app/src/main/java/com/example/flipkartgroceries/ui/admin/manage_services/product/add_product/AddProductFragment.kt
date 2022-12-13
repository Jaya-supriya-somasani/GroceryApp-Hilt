package com.example.flipkartgroceries.ui.admin.manage_services.product.add_product

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
import androidx.navigation.fragment.navArgs
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentAddProductsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddProductFragment : BaseFragment<FragmentAddProductsBinding>() {
    override fun getLayoutResource() = R.layout.fragment_add_products
    private val addProductsViewModel: AddProductViewModel by viewModels()
    private val args: AddProductFragmentArgs by navArgs()

    val selectProductImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                addProductsViewModel.selectProductImage.value = uri.toString()
                dataBinding.imageView.setImageURI(uri)
            } else {
                Log.d(TAG, "Image is not available")
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
        addProductsViewModel.productId.value = args.details.productId
        addProductsViewModel.categoryType.value = args.details.categoryName.toString()
        addProductsViewModel.productName.value = args.details.productName.toString()
        addProductsViewModel.productWeight.value = args.details.productWeight.toString()
        addProductsViewModel.productMRP.value = args.details.productMRP.toString()
        addProductsViewModel.productPrice.value = args.details.productPrice.toString()
        addProductsViewModel.productDescription.value = args.details.description.toString()
        addProductsViewModel.selectProductImage.value = args.details.productImage.toString()

        lifecycleScope.launchWhenResumed {
            addProductsViewModel.selectImageEvent.collectLatest {
                selectProductImage.launch("image/*")
            }
        }

        lifecycleScope.launchWhenResumed {
            addProductsViewModel.categoryTypeError.collectLatest {
                dataBinding.addCategoryType.error = addProductsViewModel.categoryTypeError.value
            }
        }
        lifecycleScope.launchWhenResumed {
            addProductsViewModel.productNameError.collectLatest {
                dataBinding.addProductNameTv.error = addProductsViewModel.productNameError.value
            }
        }
        lifecycleScope.launchWhenResumed {
            addProductsViewModel.productWeightError.collectLatest {
                dataBinding.productWeightTv.error = addProductsViewModel.productWeightError.value
            }
        }
        lifecycleScope.launchWhenResumed {
            addProductsViewModel.productMrpError.collectLatest {
                dataBinding.productMRPTv.error = addProductsViewModel.productMrpError.value
            }
        }
        lifecycleScope.launchWhenResumed {
            addProductsViewModel.productPriceError.collectLatest {
                dataBinding.productPriceTv.error = addProductsViewModel.productPriceError.value
            }
        }


        lifecycleScope.launchWhenResumed {
            addProductsViewModel.addProductEvent.collectLatest {
                val action =
                    AddProductFragmentDirections.actionAddProductsFragmentToViewProductsFragment()
                findNavController().navigate(action)

            }
        }
        lifecycleScope.launchWhenResumed {
            addProductsViewModel.toastEvent.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initToolbar() {
        dataBinding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}