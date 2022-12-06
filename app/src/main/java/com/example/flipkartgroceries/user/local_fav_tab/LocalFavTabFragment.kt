package com.example.flipkartgroceries.user.local_fav_tab

import androidx.fragment.app.viewModels
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentLocalFavTabBinding

class LocalFavTabFragment : BaseFragment<FragmentLocalFavTabBinding>() {
    override fun getLayoutResource() = R.layout.fragment_local_fav_tab
    private val localFavTabViewModel: LocalFavTabViewModel by viewModels()
    override fun setUp() {
        dataBinding.viewModel = localFavTabViewModel
    }


}