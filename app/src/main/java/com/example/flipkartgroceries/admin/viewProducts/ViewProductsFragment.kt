package com.example.flipkartgroceries.admin.viewProducts

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.database.ProductsEntity
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

    private fun editButtonClicked(item: ProductsEntity) {
        val productId = adapter.itemPosition.productId
        val categoryName = adapter.itemPosition.categoryName
        val productName = adapter.itemPosition.productName
        val productWeight = adapter.itemPosition.productWeight
        val productMRP = adapter.itemPosition.productMRP
        val productPrice = adapter.itemPosition.productPrice
        val description = adapter.itemPosition.description
        val productImg = adapter.itemPosition.productImage
        val action = ViewProductsFragmentDirections.actionViewProductsFragmentToAddProductsFragment(
            ProductsEntity(
                productId = productId,
                categoryName = categoryName,
                productName = productName,
                productWeight = productWeight,
                productMRP = productMRP,
                productPrice = productPrice,
                description = description,
                productImage = productImg
            )
        )
        findNavController().navigate(action)
    }
}