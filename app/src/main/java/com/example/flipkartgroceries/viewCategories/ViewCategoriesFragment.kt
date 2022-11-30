package com.example.flipkartgroceries.viewCategories

import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentViewCategoriesBinding

class ViewCategoriesFragment :
    BaseFragment<FragmentViewCategoriesBinding, ViewCategoriesViewModel>() {
    override fun getViewModel()=ViewCategoriesViewModel::class.java

    override fun getLayoutResource()=R.layout.fragment_view_categories

    override fun setUp() {
        dataBinding.viewModel=viewModel
    }


}