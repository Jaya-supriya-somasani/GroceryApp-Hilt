package com.example.flipkartgroceries.viewProducts

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentViewProductsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ViewProductsFragment : BaseFragment<FragmentViewProductsBinding>() {
    private val viewProductsViewModel : ViewProductsViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_view_products
    var adapter=ViewProductsAdapter()

    override fun setUp() {
        dataBinding.viewModel = viewProductsViewModel
        dataBinding.recyclerView.adapter=adapter
        initToolbar()
        lifecycleScope.launchWhenResumed {
            viewProductsViewModel.productsList.collectLatest {
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