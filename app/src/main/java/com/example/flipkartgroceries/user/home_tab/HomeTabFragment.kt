package com.example.flipkartgroceries.user.home_tab

import androidx.fragment.app.viewModels
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentHomeTabBinding

class HomeTabFragment : BaseFragment<FragmentHomeTabBinding>() {
    override fun getLayoutResource() = R.layout.fragment_home_tab
    private val homeTabViewModel: HomeTabViewModel by viewModels()

    override fun setUp() {
        dataBinding.viewModel = homeTabViewModel
    }
}