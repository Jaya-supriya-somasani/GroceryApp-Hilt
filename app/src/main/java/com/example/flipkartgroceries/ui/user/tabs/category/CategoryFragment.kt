package com.example.flipkartgroceries.ui.user.tabs.category

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentCategoriesTabBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoriesTabBinding>() {
    override fun getLayoutResource() = R.layout.fragment_categories_tab
    private val categoryTabViewModel: CategoryViewModel by viewModels()

    val adapter = CategoryItemsAdapter()
    override fun setUp() {
        dataBinding.viewModel = categoryTabViewModel
        dataBinding.categoryRecyclerView.adapter = adapter

        lifecycleScope.launchWhenResumed {
            categoryTabViewModel.categoryList.collectLatest {
                adapter.submitList(it)
            }
        }
    }
}