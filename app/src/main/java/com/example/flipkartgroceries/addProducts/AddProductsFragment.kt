package com.example.flipkartgroceries.addProducts

import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentAddProductsBinding

class AddProductsFragment : BaseFragment<FragmentAddProductsBinding,AddProductsViewModel>() {
    override fun getViewModel()=AddProductsViewModel::class.java

    override fun getLayoutResource()=R.layout.fragment_add_products

    override fun setUp() {
       dataBinding.viewModel=viewModel
    }


}