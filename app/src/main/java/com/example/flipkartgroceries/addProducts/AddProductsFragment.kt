package com.example.flipkartgroceries.addProducts

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentAddProductsBinding
import kotlinx.coroutines.flow.collectLatest

class AddProductsFragment : BaseFragment<FragmentAddProductsBinding, AddProductsViewModel>() {
    override fun getViewModel() = AddProductsViewModel::class.java

    override fun getLayoutResource() = R.layout.fragment_add_products

    override fun setUp() {
        dataBinding.viewModel = viewModel

        lifecycleScope.launchWhenResumed {
            viewModel.addProductEvent.collectLatest {
                val action =
                    AddProductsFragmentDirections.actionAddProductsFragmentToViewProductsFragment()
                findNavController().navigate(action)
            }
        }
    }


}