package com.example.flipkartgroceries.viewProducts

import androidx.fragment.app.viewModels
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentViewProductsBinding

class ViewProductsFragment : BaseFragment<FragmentViewProductsBinding>() {
    private val viewProductsViewModel : ViewProductsViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_view_products

    override fun setUp() {
        dataBinding.viewModel = viewProductsViewModel
    }
}