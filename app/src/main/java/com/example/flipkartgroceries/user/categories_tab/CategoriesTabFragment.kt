package com.example.flipkartgroceries.user.categories_tab

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentCategoriesTabBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CategoriesTabFragment : BaseFragment<FragmentCategoriesTabBinding>() {
    override fun getLayoutResource() = R.layout.fragment_categories_tab
    private val categoryTabViewModel: CategoriesTabViewModel by viewModels()

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