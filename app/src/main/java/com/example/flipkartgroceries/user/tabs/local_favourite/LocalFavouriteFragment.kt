package com.example.flipkartgroceries.user.tabs.local_favourite

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentLocalFavTabBinding
import com.example.flipkartgroceries.user.tabs.home.HomeFrequentlyBoughtAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LocalFavouriteFragment : BaseFragment<FragmentLocalFavTabBinding>() {
    override fun getLayoutResource() = R.layout.fragment_local_fav_tab
    private val localFavTabViewModel: LocalFavouriteViewModel by viewModels()
    val adapter= HomeFrequentlyBoughtAdapter()
    val riceProductAdapter= RiceProductAdapter()
    override fun setUp() {
        dataBinding.viewModel = localFavTabViewModel
        dataBinding.riceDalRecyclerView.adapter=riceProductAdapter
        dataBinding.chocolateRecyclerVW.adapter=adapter
        dataBinding.oilRecyclerView.adapter=adapter
        dataBinding.chocolateRecyclerVW.adapter=adapter
        dataBinding.instantMixRecyclerVW.adapter=adapter

        lifecycleScope.launchWhenResumed {
            localFavTabViewModel.frequentlyBoughtList.collectLatest {
                adapter.submitList(it)
            }
        }
        lifecycleScope.launchWhenResumed {
            localFavTabViewModel.specificProduct.collectLatest {
                riceProductAdapter.submitList(it)
            }
        }
    }


}