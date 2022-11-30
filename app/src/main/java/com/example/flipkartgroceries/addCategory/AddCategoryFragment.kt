package com.example.flipkartgroceries.addCategory

import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentAddCategoryBinding

class AddCategoryFragment : BaseFragment<FragmentAddCategoryBinding, AddCategoryViewModel>() {
    override fun getViewModel()= AddCategoryViewModel::class.java

    override fun getLayoutResource()= R.layout.fragment_add_category

    override fun setUp() {
        dataBinding.viewModel=viewModel
    }


}