package com.example.flipkartgroceries.addProducts

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentAddProductsBinding
import kotlinx.coroutines.flow.collectLatest

class AddProductsFragment : BaseFragment<FragmentAddProductsBinding>() {

    private val addProductsViewModel:AddProductsViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_add_products

    override fun setUp() {
        dataBinding.viewModel = addProductsViewModel

        lifecycleScope.launchWhenResumed {
            addProductsViewModel.addProductEvent.collectLatest {
                val action =
                    AddProductsFragmentDirections.actionAddProductsFragmentToViewProductsFragment()
                findNavController().navigate(action)
            }
        }
    }


}