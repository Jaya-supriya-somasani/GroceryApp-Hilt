package com.example.flipkartgroceries.admin.manageServices


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentManageServicesBinding
import kotlinx.coroutines.flow.collectLatest

class ManageServicesFragment :
    BaseFragment<FragmentManageServicesBinding>() {

    private val manageServicesViewModel: ManageServicesViewModel by viewModels()

    override fun getLayoutResource() = R.layout.fragment_manage_services


    override fun setUp() {
        dataBinding.viewModel = manageServicesViewModel
        initToolbar()
        lifecycleScope.launchWhenResumed {
            manageServicesViewModel.addCategoryEvent.collectLatest {
                val addCategoryAction =
                    ManageServicesFragmentDirections.actionManageServicesFragmentToAddCategoryFragment()
                findNavController().navigate(addCategoryAction)
            }
        }
        lifecycleScope.launchWhenResumed {
            manageServicesViewModel.viewCategoriesEvent.collectLatest {
                val viewCategoriesAction =
                    ManageServicesFragmentDirections.actionManageServicesFragmentToViewCategoriesFragment()
                findNavController().navigate(viewCategoriesAction)
            }
        }
        lifecycleScope.launchWhenResumed {
            manageServicesViewModel.addProductsEvent.collectLatest {
                val addProductsAction =
                    ManageServicesFragmentDirections.actionManageServicesFragmentToAddProductsFragment()
                findNavController().navigate(addProductsAction)
            }
        }
        lifecycleScope.launchWhenResumed {
            manageServicesViewModel.viewProductsChannel.collectLatest {
                val viewProductsAction =
                    ManageServicesFragmentDirections.actionManageServicesFragmentToViewProductsFragment()
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