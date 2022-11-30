package com.example.flipkartgroceries.home

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun getLayoutResource() = R.layout.fragment_home
    override fun getViewModel() = HomeViewModel::class.java


    override fun setUp() {
        dataBinding.viewModel = viewModel
        lifecycleScope.launchWhenResumed {
            viewModel.manageServiceEvent.collectLatest {
                val action = HomeFragmentDirections.actionHomeFragmentToManageServicesFragment()
                findNavController().navigate(action)
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.userHomeEvent.collectLatest {
                val action = HomeFragmentDirections.actionHomeFragmentToUserHomeFragment()
                findNavController().navigate(action)
            }
        }

    }
}