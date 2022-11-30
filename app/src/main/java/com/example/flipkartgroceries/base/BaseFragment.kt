package com.example.flipkartgroceries.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<Binding : ViewDataBinding, VM : ViewModel> : Fragment() {
    abstract fun getViewModel():Class<VM>
    lateinit var viewModel: VM
    lateinit var dataBinding: Binding
    abstract fun getLayoutResource(): Int
    abstract fun setUp()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding=DataBindingUtil.inflate(inflater,getLayoutResource(),container,false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this)[getViewModel()]
        dataBinding.lifecycleOwner=viewLifecycleOwner
        setUp()
    }
}