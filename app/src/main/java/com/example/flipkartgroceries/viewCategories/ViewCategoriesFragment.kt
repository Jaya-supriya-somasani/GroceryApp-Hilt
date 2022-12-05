package com.example.flipkartgroceries.viewCategories

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentViewCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ViewCategoriesFragment :
    BaseFragment<FragmentViewCategoriesBinding>() {
    private val viewCategoryViewModel: ViewCategoriesViewModel by viewModels()

    override fun getLayoutResource() = R.layout.fragment_view_categories

    var adapter = ViewCategoryAdapter()

    override fun setUp() {
        dataBinding.viewModel = viewCategoryViewModel
        dataBinding.recyclerView.adapter=adapter
        initToolbar()
        lifecycleScope.launchWhenResumed {
            viewCategoryViewModel.list.collectLatest {
                adapter.submitList(it)
            }
        }
    }
    private fun initToolbar(){
        dataBinding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}