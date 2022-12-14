package com.example.flipkartgroceries.ui.admin.manage_services.category.view_category

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.database.CategoryEntity
import com.example.flipkartgroceries.databinding.FragmentViewCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ViewCategoryFragment :
    BaseFragment<FragmentViewCategoriesBinding>() {
    private val viewCategoryViewModel: ViewCategoryViewModel by viewModels()

    override fun getLayoutResource() = R.layout.fragment_view_categories

    var adapter = ViewCategoryAdapter {
        editBtnClicked(it)
    }

    override fun setUp() {
        dataBinding.viewModel = viewCategoryViewModel
        dataBinding.recyclerView.adapter = adapter
        initToolbar()
        lifecycleScope.launchWhenResumed {
            viewCategoryViewModel.list.collectLatest {
                adapter.submitList(it)
            }
        }
    }
    private fun initToolbar() {
        dataBinding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun editBtnClicked(item: CategoryEntity) {
        val action =
            ViewCategoryFragmentDirections.actionViewCategoriesFragmentToAddCategoryFragment(
                CategoryEntity(item.categoryId, item.categoryImage, item.categoryName), isUpdated = true)
        findNavController().navigate(action)
    }
}