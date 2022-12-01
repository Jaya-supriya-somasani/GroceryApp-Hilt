package com.example.flipkartgroceries.viewCategories

import androidx.fragment.app.viewModels
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentViewCategoriesBinding

class ViewCategoriesFragment :
    BaseFragment<FragmentViewCategoriesBinding>() {
    private val viewCategoryViewModel: ViewCategoriesViewModel by viewModels()

    override fun getLayoutResource()=R.layout.fragment_view_categories

    val adapter=ViewCategoryAdapter()

    override fun setUp() {
        dataBinding.viewModel=viewCategoryViewModel
    }


}