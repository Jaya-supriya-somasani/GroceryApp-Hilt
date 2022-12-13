package com.example.flipkartgroceries.ui.admin.manage_services


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.database.CategoryEntity
import com.example.flipkartgroceries.database.ProductEntity
import com.example.flipkartgroceries.databinding.FragmentManageServicesBinding
import kotlinx.coroutines.flow.collectLatest

class ManageServiceFragment :
    BaseFragment<FragmentManageServicesBinding>() {

    private val manageServiceViewModel: ManageServiceViewModel by viewModels()

    override fun getLayoutResource() = R.layout.fragment_manage_services


    override fun setUp() {
        dataBinding.viewModel = manageServiceViewModel
        initToolbar()
        lifecycleScope.launchWhenResumed {
            manageServiceViewModel.addCategoryEvent.collectLatest {
                val addCategoryAction =
                    ManageServiceFragmentDirections.actionManageServicesFragmentToAddCategoryFragment(
                        CategoryEntity(0, null, null)
                    )
                findNavController().navigate(addCategoryAction)
            }
        }
        lifecycleScope.launchWhenResumed {
            manageServiceViewModel.viewCategoriesEvent.collectLatest {
                val viewCategoriesAction =
                    ManageServiceFragmentDirections.actionManageServicesFragmentToViewCategoriesFragment()
                findNavController().navigate(viewCategoriesAction)
            }
        }
        lifecycleScope.launchWhenResumed {
            manageServiceViewModel.addProductsEvent.collectLatest {
                val addProductsAction =
                    ManageServiceFragmentDirections.actionManageServicesFragmentToAddProductsFragment(
                        ProductEntity(0, null, null, null, null, null, null, null)
                    )
                findNavController().navigate(addProductsAction)
            }
        }
        lifecycleScope.launchWhenResumed {
            manageServiceViewModel.viewProductsChannel.collectLatest {
                val viewProductsAction =
                    ManageServiceFragmentDirections.actionManageServicesFragmentToViewProductsFragment()
                findNavController().navigate(viewProductsAction)
            }
        }
    }

    private fun initToolbar() {
        dataBinding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}