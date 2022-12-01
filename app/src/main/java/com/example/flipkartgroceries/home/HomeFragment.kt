package com.example.flipkartgroceries.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getLayoutResource() = R.layout.fragment_home
private val homeViewModel:HomeViewModel by viewModels()

    override fun setUp() {
        dataBinding.viewModel = homeViewModel
        lifecycleScope.launchWhenResumed {
            homeViewModel.manageServiceEvent.collectLatest {
                val action = HomeFragmentDirections.actionHomeFragmentToManageServicesFragment()
                findNavController().navigate(action)
            }
        }
        lifecycleScope.launchWhenResumed {
            homeViewModel.userHomeEvent.collectLatest {
                val action = HomeFragmentDirections.actionHomeFragmentToUserHomeFragment()
                findNavController().navigate(action)
            }
        }

    }
}