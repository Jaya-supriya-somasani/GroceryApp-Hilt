package com.example.flipkartgroceries.ui.user.tabs.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentHomeTabBinding
import com.example.flipkartgroceries.ui.user.tabs.category.CategoryItemsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeTabBinding>() {
    override fun getLayoutResource() = R.layout.fragment_home_tab
    private val homeTabViewModel: HomeViewModel by viewModels()
    var categoryAdapter = HomeCategoryListAdapter()
    var frequentlyBoughtAdapter= HomeFrequentlyBoughtAdapter()
    var categoryItemsAdapter= CategoryItemsAdapter()

    override fun setUp() {
        dataBinding.viewModel = homeTabViewModel
        dataBinding.categoryRecyclerView.adapter = categoryAdapter
        dataBinding.frequentlyBoughtRecyclerVW.adapter=frequentlyBoughtAdapter
        dataBinding.categoryItemsRecyclerView.adapter=categoryItemsAdapter
        lifecycleScope.launchWhenResumed {
            homeTabViewModel.categoriesDetailsList.collectLatest {
                categoryAdapter.submitList(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            homeTabViewModel.frequentlyBoughtProductsList.collectLatest {
                frequentlyBoughtAdapter.submitList(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            homeTabViewModel.categoriesDetailsList.collectLatest {
                categoryItemsAdapter.submitList(it)
            }
        }
    }
}