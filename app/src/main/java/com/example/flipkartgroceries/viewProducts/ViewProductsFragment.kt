package com.example.flipkartgroceries.viewProducts

import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentViewProductsBinding

class ViewProductsFragment : BaseFragment<FragmentViewProductsBinding, ViewProductsViewModel>() {
    override fun getViewModel() = ViewProductsViewModel::class.java
    override fun getLayoutResource() = R.layout.fragment_view_products

    override fun setUp() {
        dataBinding.viewModel = viewModel
    }
}