package com.example.flipkartgroceries.user.home_tab

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentHomeTabBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeTabFragment : BaseFragment<FragmentHomeTabBinding>() {
    override fun getLayoutResource() = R.layout.fragment_home_tab
    private val homeTabViewModel: HomeTabViewModel by viewModels()
    var adapter = HomeTabCategoryListAdapter()

    override fun setUp() {
        dataBinding.viewModel = homeTabViewModel
        dataBinding.categoryRecyclerView.adapter = adapter
        lifecycleScope.launchWhenResumed {
            homeTabViewModel.categoriesDetailsList.collectLatest {
                adapter.submitList(it)
            }
        }
    }
}