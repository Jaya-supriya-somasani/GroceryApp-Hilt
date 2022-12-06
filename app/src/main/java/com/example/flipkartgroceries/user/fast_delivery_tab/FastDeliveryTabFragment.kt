package com.example.flipkartgroceries.user.fast_delivery_tab

import androidx.fragment.app.viewModels
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentFastDeliveryTabBinding

class FastDeliveryTabFragment : BaseFragment<FragmentFastDeliveryTabBinding>() {
    override fun getLayoutResource() = R.layout.fragment_fast_delivery_tab
    private val fastDeliveryTabViewModel: FastDeliveryTabViewModel by viewModels()

    override fun setUp() {
        dataBinding.viewModel = fastDeliveryTabViewModel
    }


}