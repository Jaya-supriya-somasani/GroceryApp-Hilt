package com.example.flipkartgroceries.user.categories_tab

import androidx.fragment.app.viewModels
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentCategoriesTabBinding

class CategoriesTabFragment : BaseFragment<FragmentCategoriesTabBinding>() {
    override fun getLayoutResource() = R.layout.fragment_categories_tab
    private val categoryTabViewModel: CategoriesTabViewModel by viewModels()

    override fun setUp() {
        dataBinding.viewModel = categoryTabViewModel
    }


}