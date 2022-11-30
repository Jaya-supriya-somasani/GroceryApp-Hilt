package com.example.flipkartgroceries.manageServices


import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentManageServicesBinding
import kotlinx.coroutines.flow.collectLatest

class ManageServicesFragment :
    BaseFragment<FragmentManageServicesBinding, ManageServicesViewModel>() {

    override fun getViewModel() = ManageServicesViewModel::class.java

    override fun getLayoutResource() = R.layout.fragment_manage_services


    override fun setUp() {
        dataBinding.viewModel = viewModel

        lifecycleScope.launchWhenResumed {
            viewModel.addCategoryEvent.collectLatest {
                val addCategoryAction =
                    ManageServicesFragmentDirections.actionManageServicesFragmentToAddCategoryFragment()
                findNavController().navigate(addCategoryAction)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.viewCategoriesEvent.collectLatest {
                val viewCategoriesAction =
                    ManageServicesFragmentDirections.actionManageServicesFragmentToViewCategoriesFragment()
                findNavController().navigate(viewCategoriesAction)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.addProductsEvent.collectLatest {
                val addProductsAction =
                    ManageServicesFragmentDirections.actionManageServicesFragmentToAddProductsFragment()
                findNavController().navigate(addProductsAction)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.viewProductsChannel.collectLatest {
                val viewProductsAction =
                    ManageServicesFragmentDirections.actionManageServicesFragmentToViewProductsFragment()
                findNavController().navigate(viewProductsAction)
            }
        }

    }

}