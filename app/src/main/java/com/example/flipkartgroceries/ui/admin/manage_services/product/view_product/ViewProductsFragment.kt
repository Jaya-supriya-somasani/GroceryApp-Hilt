package com.example.flipkartgroceries.ui.admin.manage_services.product.view_product

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.database.ProductEntity
import com.example.flipkartgroceries.databinding.FragmentViewProductsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ViewProductsFragment : BaseFragment<FragmentViewProductsBinding>() {
    private val viewProductsViewModel: ViewProductsViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_view_products
    var adapter = ViewProductsAdapter {
        editButtonClicked(it)
    }

    override fun setUp() {
        dataBinding.viewModel = viewProductsViewModel
        dataBinding.recyclerView.adapter = adapter
        initToolbar()
        lifecycleScope.launchWhenResumed {
            viewProductsViewModel.productsList.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    private fun initToolbar() {
        dataBinding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun editButtonClicked(item: ProductEntity) {
        val action = ViewProductsFragmentDirections.actionViewProductsFragmentToAddProductsFragment(
            ProductEntity(
                productId = item.productId,
                categoryName = item.categoryName,
                productName = item.productName,
                productWeight = item.productWeight,
                productMRP = item.productMRP,
                productPrice = item.productPrice,
                description = item.description,
                productImage = item.productImage
            )
        )
        findNavController().navigate(action)
    }
}