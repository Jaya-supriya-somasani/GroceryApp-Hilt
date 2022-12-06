package com.example.flipkartgroceries.admin.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentAdminHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AdminHomeFragment : BaseFragment<FragmentAdminHomeBinding>() {
    override fun getLayoutResource() = R.layout.fragment_admin_home
private val homeViewModel: AdminHomeViewModel by viewModels()

    override fun setUp() {
        dataBinding.viewModel = homeViewModel
        lifecycleScope.launchWhenResumed {
            homeViewModel.manageServiceEvent.collectLatest {
                val action = AdminHomeFragmentDirections.actionAdminHomeFragmentToManageServicesFragment()
                findNavController().navigate(action)
            }
        }
        lifecycleScope.launchWhenResumed {
            homeViewModel.userHomeEvent.collectLatest {
                val action = AdminHomeFragmentDirections.actionHomeFragmentToUserHomeFragment()
                findNavController().navigate(action)
            }
        }

    }
}