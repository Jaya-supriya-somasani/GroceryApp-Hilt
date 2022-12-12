package com.example.flipkartgroceries.user.local_fav_tab

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentLocalFavTabBinding
import com.example.flipkartgroceries.user.home_tab.HomeFrequentlyBoughtAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LocalFavTabFragment : BaseFragment<FragmentLocalFavTabBinding>() {
    override fun getLayoutResource() = R.layout.fragment_local_fav_tab
    private val localFavTabViewModel: LocalFavTabViewModel by viewModels()
    val adapter= HomeFrequentlyBoughtAdapter()
    val riceProductAdapter=RiceProductAdapter()
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